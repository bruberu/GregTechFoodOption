package gregtechfoodoption.mixins.early;

import com.llamalad7.mixinextras.sugar.Local;
import gregtechfoodoption.potion.AntiSchizoPotion;
import gregtechfoodoption.utils.GTFOLog;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(value = GuiPlayerTabOverlay.class, priority = 500, remap = false)
public class GuiPlayerTabOverlayMixin {
    @Inject(method = "renderPlayerlist", at = @At(value = "INVOKE_ASSIGN", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void youAreAllAlone(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn, CallbackInfo ci, NetHandlerPlayClient nethandlerplayclient, @Local List<NetworkPlayerInfo> list) {
        if (!Minecraft.getMinecraft().player.isPotionActive(AntiSchizoPotion.INSTANCE)) {
            return;
        }
        list.removeIf(playerInfo -> {
            if (!playerInfo.getGameProfile().getName().equals(Minecraft.getMinecraft().player.getName())) {
                return true;
            }
            return false;
        });
    }
}

package gregtechfoodoption.mixins.early;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = GuiPlayerTabOverlay.class, priority = 500, remap = false)
public class GuiPlayerTabOverlayMixin {
    @Inject(method = "renderPlayerlist", at = @At(value = "INVOKE_ASSIGN", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;"))
    public void youAreAllAlone(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn, CallbackInfo ci, @Local List<NetworkPlayerInfo> list) {
        list.removeIf(playerInfo -> !playerInfo.getGameProfile().getId().equals(Minecraft.getMinecraft().player.getPersistentID()));
    }
}

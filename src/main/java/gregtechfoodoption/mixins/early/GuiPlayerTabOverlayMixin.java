package gregtechfoodoption.mixins.early;

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
    @Inject(method = "renderPlayerlist", at = @At(value = "INVOKE_ASSIGN", target = "Lcom/google/common/collect/Ordering;sortedCopy(Ljava/lang/Iterable;)Ljava/util/List;"),
            cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
    public void youAreAllAlone(int width, Scoreboard scoreboardIn, ScoreObjective scoreObjectiveIn, CallbackInfo ci, NetHandlerPlayClient nethandlerplayclient, List<NetworkPlayerInfo> list) {
        list.removeIf(playerInfo -> !playerInfo.getGameProfile().getId().equals(Minecraft.getMinecraft().player.getPersistentID()));
    }
}

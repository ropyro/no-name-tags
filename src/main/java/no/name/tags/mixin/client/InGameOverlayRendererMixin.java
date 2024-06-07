package no.name.tags.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import no.name.tags.NoNameTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    @Inject(method = "renderFireOverlay", cancellable = true, at = @At(value = "HEAD"))
    private static void renderFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo info) {
        if (NoNameTags.hideFire) {
            info.cancel();
            if(client.player.isOnFire()){
                client.player.sendMessage(Text.of("You are on fire."), true);
            }
        }
    }
}
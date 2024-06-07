package no.name.tags.mixin.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import no.name.tags.NoNameTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameGuiMixin {

    @Inject(method = "renderHotbar", cancellable = true, at = @At(value = "HEAD"))
    private void renderHotbar(float tickDelta, DrawContext context, CallbackInfo info) {
        if (NoNameTags.hideHotBar) {
            info.cancel();
        }
    }

    @Inject(method = "renderStatusBars", cancellable = true, at = @At(value = "HEAD"))
    private void renderStatusBars(DrawContext context, CallbackInfo info) {
        if (NoNameTags.hideHotBar) {
            info.cancel();
        }
    }

    @Inject(method = "renderExperienceBar", cancellable = true, at = @At(value = "HEAD"))
    public void renderExperienceBar(DrawContext context, int x, CallbackInfo info) {
        if (NoNameTags.hideHotBar) {
            info.cancel();
        }
    }
}
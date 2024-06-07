package no.name.tags.mixin.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import no.name.tags.NoNameTags;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(
            method = "onKey",
            at = @At(
                    value = "HEAD"
            )
    )
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (client.getWindow().getHandle() == window) {
            if (action != GLFW.GLFW_RELEASE && NoNameTags.keyHideNameTags.matchesKey(key, scancode)) {
                NoNameTags.hideNameTags = !NoNameTags.hideNameTags;
                if (client.player != null) {
                    String state = NoNameTags.hideNameTags ? "disabled" : "enabled";
                    client.player.sendMessage(Text.of("Name tag rendering has been " + state + " !"), true);
                }
            } else if (action != GLFW.GLFW_RELEASE && NoNameTags.keyHideHotBar.matchesKey(key, scancode)) {
                NoNameTags.hideHotBar = !NoNameTags.hideHotBar;
                if (client.player != null) {
                    String state = NoNameTags.hideHotBar ? "disabled" : "enabled";
                    client.player.sendMessage(Text.of("Hot bar rendering has been " + state + " !"), true);
                }
            } else if (action != GLFW.GLFW_RELEASE && NoNameTags.keyHideFire.matchesKey(key, scancode)) {
                NoNameTags.hideFire = !NoNameTags.hideFire;
                if (client.player != null) {
                    String state = NoNameTags.hideFire ? "disabled" : "enabled";
                    client.player.sendMessage(Text.of("Fire rendering has been " + state + " !"), true);
                }
            }
        }
    }
}

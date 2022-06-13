package no.name.tags.mixin.client;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;

import no.name.tags.NoNameTags;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {

	@Shadow @Final private Minecraft minecraft;

	@Inject(
		method = "keyPress",
		at = @At(
			value = "HEAD"
		)
	)
	private void onKeyPress(long window, int key, int scancode, int action, int mods, CallbackInfo ci) {
		if (minecraft.getWindow().getWindow() == window) {
			if (action != GLFW.GLFW_RELEASE && NoNameTags.keyHideNameTags.matches(key, scancode)) {
				NoNameTags.hideNameTags = !NoNameTags.hideNameTags;

				if (minecraft.player != null) {
					String state = NoNameTags.hideNameTags ? "disabled" : "enabled";
					Component message = new TextComponent(String.format("Name tag rendering has been %s!", state));

					minecraft.player.displayClientMessage(message, true);
				}
			}
		}
	}
}

package no.name.tags.mixin.client;

import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Options;

import no.name.tags.NoNameTags;

@Mixin(Options.class)
public class OptionsMixin {

	@Shadow @Final @Mutable private KeyMapping[] keyMappings;

	@Inject(
		method = "load",
		at = @At(
			value = "HEAD"
		)
	)
	private void registerKeyMapping(CallbackInfo ci) {
		if (NoNameTags.keyHideNameTags == null) {
			NoNameTags.keyHideNameTags = new KeyMapping("Hide Name Tags", GLFW.GLFW_KEY_F6, "key.categories.misc");

			KeyMapping[] array = keyMappings;
			keyMappings = new KeyMapping[array.length + 1];

			for (int i = 0; i < array.length; i++) {
				keyMappings[i] = array[i];
			}

			keyMappings[array.length] = NoNameTags.keyHideNameTags;
		}
	}
}

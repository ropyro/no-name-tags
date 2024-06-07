package no.name.tags.mixin.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import no.name.tags.NoNameTags;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(GameOptions.class)
public class OptionsMixin {

	@Shadow @Final @Mutable private KeyBinding[] allKeys;

	@Inject(
			method = "load",
			at = @At(
					value = "HEAD"
			)
	)
	private void registerKeyMapping(CallbackInfo ci) {
		// Check if the key binding is already initialized
		if (NoNameTags.keyHideNameTags == null && NoNameTags.keyHideHotBar == null) {
			NoNameTags.keyHideNameTags = new KeyBinding("Hide Nametags", GLFW.GLFW_KEY_F6, "key.categories.misc");
			NoNameTags.keyHideHotBar = new KeyBinding("Hide Hotbar", GLFW.GLFW_KEY_F7, "key.categories.misc");

			// Create a list to store the key bindings
			List<KeyBinding> keyBindings = new ArrayList<>(Arrays.asList(allKeys));
			keyBindings.add(NoNameTags.keyHideHotBar);
			keyBindings.add(NoNameTags.keyHideNameTags);

			// Update the allKeys array
			allKeys = keyBindings.toArray(new KeyBinding[0]);

			// Register the new key bindings
			KeyBindingHelper.registerKeyBinding(NoNameTags.keyHideHotBar);
			KeyBindingHelper.registerKeyBinding(NoNameTags.keyHideNameTags);
		}
	}
}

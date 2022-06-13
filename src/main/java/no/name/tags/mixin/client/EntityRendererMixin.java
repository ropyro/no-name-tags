package no.name.tags.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.entity.EntityRenderer;

import no.name.tags.NoNameTags;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

	@Inject(
		method = "renderNameTag",
		cancellable = true,
		at = @At(
			value = "HEAD"
		)
	)
	private void cancelRenderNameTag(CallbackInfo ci) {
		if (NoNameTags.hideNameTags) {
			ci.cancel();
		}
	}
}

package no.name.tags.mixin.client;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import no.name.tags.NoNameTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class EntityRendererMixin<T extends LivingEntity> {

	@Inject(
			method = "hasLabel",
			cancellable = true,
			at = @At(
					value = "HEAD"
			)
	)
	protected void hasLabel(T livingEntity, CallbackInfoReturnable<Boolean> info) {
		if (NoNameTags.hideNameTags) {
			info.setReturnValue(false);
			info.cancel();
		}
	}
}

package no.name.tags.mixin.client;

import net.minecraft.entity.Entity;
import no.name.tags.NoNameTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Entity.class)
public class EntityMixin {

    @Inject(
            method = "isOnFire",
            cancellable = true,
            at = @At(
                    value = "HEAD"
            )
    )
    protected void isOnFire(CallbackInfoReturnable<Boolean> info) {
        if(NoNameTags.hideFire){
            info.setReturnValue(false);
        }
    }
}
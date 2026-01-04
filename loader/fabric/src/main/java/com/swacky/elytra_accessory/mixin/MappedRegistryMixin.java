package com.swacky.elytra_accessory.mixin;

import com.swacky.elytra_accessory.event.CommonCallbacks;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MappedRegistry.class)
public class MappedRegistryMixin {
    @SuppressWarnings("unchecked")
    @Inject(method = "freeze", at = @At(value = "RETURN", ordinal = 1))
    public <T> void freeze(CallbackInfoReturnable<Registry<T>> cir) {
        Registry<T> inst = cir.getReturnValue();

        if (inst.key().equals(Registries.ITEM)) {
            CommonCallbacks.onPostItemRegistry((Registry<Item>) inst);
        }
    }
}

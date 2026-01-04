package com.swacky.elytra_accessory.mixin;

import com.swacky.elytra_accessory.event.CommonCallbacks;
import net.minecraft.world.entity.LivingEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(
            method = "updateFallFlying",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/Util;getRandom(Ljava/util/List;Lnet/minecraft/util/RandomSource;)Ljava/lang/Object;"),
            cancellable = true)
    public void updateFallFlying(CallbackInfo ci) {
        if (CommonCallbacks.onElytraFlight((LivingEntity) (Object) this, true)) {
            ci.cancel();
        }
    }

    @Inject(
            method = "canGlide",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/entity/EquipmentSlot;VALUES:Ljava/util/List;",
                    opcode = Opcodes.GETSTATIC),
            cancellable = true)
    public void canGlide(CallbackInfoReturnable<Boolean> cir) {
        if (CommonCallbacks.onElytraFlight((LivingEntity) (Object) this, false)) {
            cir.setReturnValue(true);
        }
    }
}

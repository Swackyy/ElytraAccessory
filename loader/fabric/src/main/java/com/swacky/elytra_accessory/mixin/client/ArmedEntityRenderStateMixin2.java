package com.swacky.elytra_accessory.mixin.client;

import com.swacky.elytra_accessory.mixinutils.AccessoryRenderStateData;
import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmedEntityRenderState.class)
abstract class ArmedEntityRenderStateMixin2 {
    @Inject(method = "extractArmedEntityRenderState", at = @At(value = "HEAD"))
    private static void extractArmedEntityRenderState(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver resolver, float partialTicks, CallbackInfo ci) {
        if (entity instanceof AbstractClientPlayer player) {
            state.setData(AccessoryRenderStateData.KEY, new AccessoryRenderStateData(AccessoryHelper.getStacks(player)));
        }
    }
}

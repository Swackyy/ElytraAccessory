package com.swacky.elytra_accessory.mixin.client;

import com.swacky.elytra_accessory.mixinutils.extension.ArmedEntityRenderStateExtension;
import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Used only on Forge and NeoForge
@SuppressWarnings("UnusedMixin")
@Mixin(ArmedEntityRenderState.class)
public class ArmedEntityRenderStateMixin1 implements ArmedEntityRenderStateExtension {
    @Unique
    private NonNullList<ItemStack> elytraAccessory$stacks = null;

    @Override
    public @Nullable NonNullList<ItemStack> elytraAccessory$getItems() {
        return elytraAccessory$stacks;
    }

    @Override
    public void elytraAccessory$setItems(NonNullList<ItemStack> stacks) {
        this.elytraAccessory$stacks = stacks;
    }

    @Inject(method = "extractArmedEntityRenderState", at = @At(value = "HEAD"))
    private static void extractArmedEntityRenderState(LivingEntity entity, ArmedEntityRenderState state, ItemModelResolver resolver, float partialTicks, CallbackInfo ci) {
        if (entity instanceof AbstractClientPlayer player) {
            ((ArmedEntityRenderStateExtension) state).elytraAccessory$setItems(AccessoryHelper.getStacks(player));
        }
    }
}

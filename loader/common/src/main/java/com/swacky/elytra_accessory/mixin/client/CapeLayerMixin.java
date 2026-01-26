package com.swacky.elytra_accessory.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.swacky.elytra_accessory.mixinutils.extension.ArmedEntityRenderStateExtension;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Used only on Forge and NeoForge
@SuppressWarnings("UnusedMixin")
@Mixin(CapeLayer.class)
abstract class CapeLayerMixin {
    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
            at = @At(
                    value = "HEAD"),
            cancellable = true)
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int i, AvatarRenderState state, float j, float k, CallbackInfo ci) {
        NonNullList<ItemStack> stacks = ((ArmedEntityRenderStateExtension) state).elytraAccessory$getItems();

        if (stacks != null) {
            for (ItemStack stack : stacks) {
                if (stack.has(DataComponents.GLIDER)) {
                    ci.cancel();
                }
            }
        }
    }
}

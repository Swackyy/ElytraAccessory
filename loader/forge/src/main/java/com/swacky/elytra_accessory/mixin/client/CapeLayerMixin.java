package com.swacky.elytra_accessory.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.swacky.elytra_accessory.event.ClientCallbacks;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CapeLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CapeLayer.class)
abstract class CapeLayerMixin {
    @Inject(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/AvatarRenderState;FF)V",
            at = @At(
                    value = "HEAD"),
            cancellable = true)
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, AvatarRenderState state, float yRot, float xRot, CallbackInfo ci) {
        if (ClientCallbacks.shouldPreventCapeRender(state)) {
            ci.cancel();
        }
    }
}

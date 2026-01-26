package com.swacky.elytra_accessory.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.swacky.elytra_accessory.mixinutils.extension.ArmedEntityRenderStateExtension;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// Used only on Forge and NeoForge
@SuppressWarnings("UnusedMixin")
@Mixin(WingsLayer.class)
public class WingsLayerMixin1 {
    @ModifyExpressionValue(
            method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;ILnet/minecraft/client/renderer/entity/state/HumanoidRenderState;FF)V",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/renderer/entity/state/HumanoidRenderState;chestEquipment:Lnet/minecraft/world/item/ItemStack;",
                    opcode = Opcodes.GETFIELD))
    public ItemStack submit(ItemStack original, @Local(argsOnly = true) HumanoidRenderState state) {
        if (!LivingEntity.canGlideUsing(original, EquipmentSlot.CHEST)) {
            NonNullList<ItemStack> stacks = ((ArmedEntityRenderStateExtension) state).elytraAccessory$getItems();

            if (stacks != null) {
                for (ItemStack stack : stacks) {
                    if (stack.has(DataComponents.GLIDER)) {
                        return stack;
                    }
                }
            }
        }

        return original;
    }
}

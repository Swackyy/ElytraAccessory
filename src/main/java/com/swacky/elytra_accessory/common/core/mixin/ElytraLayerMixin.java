package com.swacky.elytra_accessory.common.core.mixin;

import com.swacky.ohmega.api.AccessoryHelper;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ElytraLayer.class)
public class ElytraLayerMixin {
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Redirect(method = "render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/layers/ElytraLayer;shouldRender(Lnet/minecraft/world/item/ItemStack;Ljava/lang/Object;)Z"))
    public boolean render(ElytraLayer<?, ?> layer, ItemStack stack, Object obj) {
        if (obj instanceof Entity entity) {
            if (((ElytraLayer) (Object) this).shouldRender(stack, entity)) {
                return true;
            } else if (entity instanceof Player player) {
                return AccessoryHelper.hasAccessory(player, Items.ELYTRA);
            }
        }
        return false;
    }
}

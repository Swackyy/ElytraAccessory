package com.swacky.elytra_accessory.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractCraftingMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InventoryMenu.class)
abstract class InventoryMenuMixin extends AbstractCraftingMenu {
    public InventoryMenuMixin(MenuType<?> type, int id, int width, int height) {
        super(type, id, width, height);
    }

    @ModifyExpressionValue(
            method = "quickMoveStack",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/Slot;hasItem()Z",
                    ordinal = 1))
    public boolean quickMoveStack(boolean original, @Local(argsOnly = true) Player player, @Local(ordinal = 1) ItemStack stack, @Local EquipmentSlot slot) {
        // Boolean algebra -> '!(a && b)' = '!a || !b'
        return original || !player.isEquippableInSlot(stack, slot);
    }
}

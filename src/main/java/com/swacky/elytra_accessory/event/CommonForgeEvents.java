package com.swacky.elytra_accessory.event;

import com.swacky.ohmega.api.AccessoryHelper;
import com.swacky.elytra_accessory.common.core.ElytraAccessory;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ElytraAccessory.MODID)
public class CommonForgeEvents {
    @SubscribeEvent
    public static void onItemUse(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().is(Items.ELYTRA)) {
            InteractionResult result = AccessoryHelper.tryEquip(event.getEntity(), event.getHand()).getResult();
            if (result == InteractionResult.SUCCESS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
            }
        }
    }

    @SubscribeEvent
    public static void hoverText(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (stack.is(Items.ELYTRA)) {
            List<Component> tooltip = event.getToolTip();
            tooltip.add(MutableComponent.create(new TranslatableContents("item." + ElytraAccessory.MODID + ".elytra.tooltip", null, TranslatableContents.NO_ARGS)).withStyle(ChatFormatting.GRAY));
            tooltip.add(AccessoryHelper.getTypeTooltip(Items.ELYTRA));
        }
    }
}

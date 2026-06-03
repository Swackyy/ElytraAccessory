package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessory;
import com.swacky.ohmega.api.common.accessorytype.AccessoryType;
import com.swacky.ohmega.api.event.AccessoryBindEvent;
import com.swacky.ohmega.api.event.AccessoryOverrideTypesEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;

@EventBusSubscriber(modid = ElytraAccessory.MODID)
public class CommonEvents {
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        event.addPackFinders(
            Identifier.fromNamespaceAndPath(ElytraAccessory.MODID, "resourcepacks/elytra_type"),
            PackType.SERVER_DATA,
            Component.translatable("dataPack.elytra_type.name"),
            PackSource.FEATURE,
            false,
            Pack.Position.TOP);
    }

    @SubscribeEvent
    public static void onBindAccessories(AccessoryBindEvent event) {
        CommonCallbacks.bindElytras();
    }

    @SubscribeEvent
    public static void onOverrideAccessoryTypes(AccessoryOverrideTypesEvent event) {
        for (Item item : ElytraAccessory.BOUND_ITEMS) {
            event.add(item, AccessoryType.UTILITY.get(), false);
        }
    }
}

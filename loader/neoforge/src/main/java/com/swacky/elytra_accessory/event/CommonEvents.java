package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.event.AccessoryOverrideTypesEvent;
import com.swacky.ohmega.common.accessorytype.AccessoryType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;

@EventBusSubscriber(modid = ElytraAccessoryCommon.MODID)
public class CommonEvents {
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        event.addPackFinders(
            Identifier.fromNamespaceAndPath(ElytraAccessoryCommon.MODID, "resourcepacks/elytra_type"),
            PackType.SERVER_DATA,
            Component.translatable("dataPack.elytra_type.name"),
            PackSource.FEATURE,
            false,
            Pack.Position.TOP);
    }

    @SubscribeEvent
    public static void onOverrideAccessoryTypes(AccessoryOverrideTypesEvent event) {
        for (Item item : ElytraAccessoryCommon.BOUND_ITEMS) {
            event.overrideRemaps.put(item, AccessoryType.UTILITY.get());
        }
    }

    @SubscribeEvent
    public static void onServerStart(ServerStartedEvent event) {
        CommonCallbacks.bindElytras();
    }
}

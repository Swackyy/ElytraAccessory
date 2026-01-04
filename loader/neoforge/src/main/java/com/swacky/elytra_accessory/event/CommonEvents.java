package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddPackFindersEvent;

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
    public static void onSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CommonCallbacks.onPostItemRegistry(BuiltInRegistries.ITEM);
        });
    }
}

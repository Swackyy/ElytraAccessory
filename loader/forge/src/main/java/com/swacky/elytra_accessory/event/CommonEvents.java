package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessory;
import com.swacky.ohmega.api.common.accessorytype.AccessoryType;
import com.swacky.ohmega.api.event.AccessoryBindEvent;
import com.swacky.ohmega.api.event.AccessoryOverrideTypesEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = ElytraAccessory.MODID)
public class CommonEvents {
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            event.addRepositorySource(consumer -> {
                var pack = Pack.readMetaAndCreate(
                        new PackLocationInfo(
                                Identifier.fromNamespaceAndPath(ElytraAccessory.MODID, "elytra_type").toString(),
                                Component.translatable("dataPack.elytra_type.name"),
                                PackSource.FEATURE,
                                Optional.empty()),
                        new PathPackResources.PathResourcesSupplier(ModList.getModFileById(ElytraAccessory.MODID)
                                .getFile().findResource("resourcepacks/elytra_type")),
                        PackType.SERVER_DATA,
                        new PackSelectionConfig(false, Pack.Position.TOP, false));

                if (pack != null) {
                    consumer.accept(pack);
                }
            });
        }
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

package com.swacky.elytra_accessory.event;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = ElytraAccessoryCommon.MODID)
public class CommonEvents {
    @SubscribeEvent
    public static void onAddPackFinders(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            event.addRepositorySource(consumer -> {
                var pack = Pack.readMetaAndCreate(
                        new PackLocationInfo(
                                Identifier.fromNamespaceAndPath(ElytraAccessoryCommon.MODID, "elytra_type").toString(),
                                Component.translatable("dataPack.elytra_type.name"),
                                PackSource.FEATURE,
                                Optional.empty()),
                        new PathPackResources.PathResourcesSupplier(ModList.get().getModFileById(ElytraAccessoryCommon.MODID)
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
    public static void onSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CommonCallbacks.onPostItemRegistry(BuiltInRegistries.ITEM);
        });
    }
}

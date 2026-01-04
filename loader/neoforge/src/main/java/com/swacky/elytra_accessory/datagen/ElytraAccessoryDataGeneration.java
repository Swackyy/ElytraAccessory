package com.swacky.elytra_accessory.datagen;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.elytra_accessory.datagen.client.lang.EnUsProvider;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ElytraAccessoryCommon.MODID)
public final class ElytraAccessoryDataGeneration {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(true, new EnUsProvider(generator.getPackOutput()));
    }
}

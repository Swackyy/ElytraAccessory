package com.swacky.elytra_accessory.datagen;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.elytra_accessory.datagen.client.ElytraAccessoryEnUsProvider;
import com.swacky.elytra_accessory.datagen.client.ElytraAccessoryTagProvider;
import com.swacky.elytra_accessory.datagen.server.ElytraAccessoryTypeProvider;
import com.swacky.ohmega.common.init.OhmegaTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

@SuppressWarnings("unused")
public class ElytraAccessoryDataGeneration implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(ElytraAccessoryEnUsProvider::new);
        pack.addProvider(ElytraAccessoryTagProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<ElytraAccessoryTypeProvider>) ElytraAccessoryTypeProvider::new);

        generator.createBuiltinResourcePack(ElytraAccessoryCommon.PACK_ID).addProvider((output, lookupProvider) ->
                new FabricTagProvider.ItemTagProvider(output, lookupProvider) {
                    @SuppressWarnings("deprecation")
                    @Override
                    protected void addTags(HolderLookup.@NonNull Provider lookupProvider) {
                        builder(OhmegaTags.get(ElytraAccessoryCommon.id("elytra"))).add(Items.ELYTRA.builtInRegistryHolder().key());
                    }
                }
        );
    }
}

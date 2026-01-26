package com.swacky.elytra_accessory.datagen.client;

import com.swacky.ohmega.common.accessorytype.AccessoryType;
import com.swacky.ohmega.common.init.OhmegaTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ElytraAccessoryTagProvider extends FabricTagProvider.ItemTagProvider {
    public ElytraAccessoryTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void addTags(HolderLookup.@NonNull Provider lookupProvider) {
        builder(OhmegaTags.get(AccessoryType.UTILITY_ID)).add(Items.ELYTRA.builtInRegistryHolder().key());
    }
}

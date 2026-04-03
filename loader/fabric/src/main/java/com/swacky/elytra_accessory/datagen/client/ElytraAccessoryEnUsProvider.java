package com.swacky.elytra_accessory.datagen.client;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.datagen.client.OhmegaLangHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ElytraAccessoryEnUsProvider extends FabricLanguageProvider {
    public ElytraAccessoryEnUsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, "en_us", lookupProvider);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider lookupProvider, @NonNull TranslationBuilder builder) {
        builder.add("dataPack." + ElytraAccessoryCommon.MODID + ".description", "Mod resources for Elytra Accessory");

        builder.add("dataPack.elytra_type.name", "Elytra Accessory Type");
        builder.add("dataPack.elytra_type.description", "Changes the Elytra to use a dedicated accessory type");

        new OhmegaLangHelper(builder::add, ElytraAccessoryCommon.MODID).addType("elytra", "Elytra");
    }
}

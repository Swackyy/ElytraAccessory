package com.swacky.elytra_accessory.datagen.client.lang;

import com.swacky.elytra_accessory.common.core.ElytraAccessory;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsProvider extends LanguageProvider {
    public ModEnUsProvider(PackOutput output) {
        super(output, ElytraAccessory.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("dataPack." + ElytraAccessory.MODID + ".description", "Mod resources for Elytra Accessory");
        add("dataPack.elytra_acc_type.title", "Elytra Accessory Type");
        add("dataPack.elytra_acc_type.description", "Adds a dedicated Accessory Type for the Elytra");
        add("item." + ElytraAccessory.MODID + ".elytra.tooltip", "Grants winged flight");
        add("accessory_type." + ElytraAccessory.MODID + ".elytra", "Elytra");
    }
}

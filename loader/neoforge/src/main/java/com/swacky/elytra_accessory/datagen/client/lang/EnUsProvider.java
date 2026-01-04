package com.swacky.elytra_accessory.datagen.client.lang;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.datagen.OhmegaLangHelper;
import com.swacky.ohmega.common.OhmegaCommon;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public final class EnUsProvider extends LanguageProvider {
    public EnUsProvider(PackOutput output) {
        super(output, OhmegaCommon.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("dataPack." + ElytraAccessoryCommon.MODID + ".description", "Mod resources for Elytra Accessory");

        add("dataPack.elytra_type.name", "Elytra Type");
        add("dataPack.elytra_type.description", "Makes elytras use the built-in elytra type");

        new OhmegaLangHelper(this::add, ElytraAccessoryCommon.MODID).addType("elytra", "Elytra");
    }
}

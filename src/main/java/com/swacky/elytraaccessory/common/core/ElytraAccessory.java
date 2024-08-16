package com.swacky.elytraaccessory.common.core;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ElytraAccessory.MODID)
public class ElytraAccessory {
    public static final String MODID = "elytraaccessory";

    public ElytraAccessory() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EAConfig.SPEC);
    }
}

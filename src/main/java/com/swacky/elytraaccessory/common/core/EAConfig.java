package com.swacky.elytraaccessory.common.core;

import com.swacky.ohmega.api.AccessoryType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ElytraAccessory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EAConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.EnumValue<AccessoryType> ELYTRA_ACC_TYPE = BUILDER
            .comment("The accessory type of elytras, defining which slot(s) they can be placed in")
            .defineEnum("elytraAccType", AccessoryType.SPECIAL);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static AccessoryType elytraAccType;

    @SubscribeEvent
    static void onLoad(ModConfigEvent event) {
        elytraAccType = ELYTRA_ACC_TYPE.get();
    }
}

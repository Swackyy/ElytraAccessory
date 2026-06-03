package com.swacky.elytra_accessory.common;

import com.swacky.elytra_accessory.event.CommonEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

@SuppressWarnings("unused")
public class ElytraAccessoryMain implements ModInitializer {
    private static final Identifier ID = ElytraAccessory.id("elytra_type");

    @Override
    public void onInitialize() {
        CommonEvents.bootstrap();

        ResourceLoader.registerBuiltinPack(
                ID,
                FabricLoader.getInstance().getModContainer(ElytraAccessory.MODID).orElseThrow(),
                PackActivationType.NORMAL);
    }
}

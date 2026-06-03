package com.swacky.elytra_accessory.datagen.server;

import com.swacky.elytra_accessory.common.ElytraAccessoryCommon;
import com.swacky.ohmega.api.common.accessorytype.AccessoryType;
import com.swacky.ohmega.api.datagen.server.AccessoryTypeProvider;
import net.minecraft.data.PackOutput;

public class ElytraAccessoryTypeProvider extends AccessoryTypeProvider {
    public ElytraAccessoryTypeProvider(PackOutput output) {
        super(output, ElytraAccessoryCommon.MODID);
    }

    @Override
    public void addTypes() {
        add("elytra", new AccessoryType.Builder()
                .emptySlotPath("accessory_slot_elytra"));
    }
}

package com.swacky.elytra_accessory.client;

import com.swacky.elytra_accessory.event.ClientEvents;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class ElytraAccessoryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientEvents.bootstrap();
    }
}

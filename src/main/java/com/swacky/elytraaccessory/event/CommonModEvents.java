package com.swacky.elytraaccessory.event;

import com.swacky.ohmega.api.AccessoryType;
import com.swacky.ohmega.api.IAccessory;
import com.swacky.ohmega.api.events.BindAccessoriesEvent;
import com.swacky.elytraaccessory.common.core.EAConfig;
import com.swacky.elytraaccessory.common.core.ElytraAccessory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ElytraAccessory.MODID)
public class CommonModEvents {
    @SubscribeEvent
    public static void bindElytra(BindAccessoriesEvent event) {
        event.add(Items.ELYTRA, new IAccessory() {
            @Override
            public @NotNull AccessoryType getType() {
                return EAConfig.elytraAccType;
            }

            @Override
            public boolean canEquip(Player player, ItemStack stack) {
                return !player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA);
            }

            @Override
            public @NotNull SoundEvent getEquipSound() {
                return ((ElytraItem) Items.ELYTRA).getEquipSound().get();
            }
        });
    }
}

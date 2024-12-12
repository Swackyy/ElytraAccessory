package com.swacky.elytra_accessory.event;

import com.swacky.ohmega.api.AccessoryHelper;
import com.swacky.ohmega.api.IAccessory;
import com.swacky.elytra_accessory.common.core.ElytraAccessory;
import com.swacky.ohmega.common.init.OhmegaItems;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ElytraAccessory.MODID)
public class CommonModEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AccessoryHelper.bindAccessory(Items.ELYTRA, new IAccessory() {
                @Override
                public boolean canEquip(Player player, ItemStack stack) {
                    return !player.getItemBySlot(EquipmentSlot.CHEST).is(Items.ELYTRA);
                }

                @Override
                public boolean checkCompatibility(IAccessory other) {
                    return IAccessory.super.checkCompatibility(other) && other != OhmegaItems.ANGEL_RING.get();
                }

                @Override
                public boolean autoSync(ServerPlayer player) {
                    return true;
                }

                @Override
                public @NotNull Holder<SoundEvent> getEquipSound() {
                    return SoundEvents.ARMOR_EQUIP_ELYTRA;
                }
            });
        });
    }

    @SubscribeEvent
    public static void addPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA) {
            PackLocationInfo info = new PackLocationInfo("elytra_acc_type", Component.translatable("dataPack.elytra_acc_type.title"), PackSource.FEATURE, Optional.empty());
            PathPackResources.PathResourcesSupplier resources = new PathPackResources.PathResourcesSupplier(ModList.get().getModFileById(ElytraAccessory.MODID).getFile().findResource("data/" + ElytraAccessory.MODID + "/datapacks/elytra_acc_type"));
            PackSelectionConfig config = new PackSelectionConfig(false, Pack.Position.TOP, false);
            Pack pack = Pack.readMetaAndCreate(info, resources, PackType.SERVER_DATA, config);
            event.addRepositorySource((consumer) -> consumer.accept(pack));
        }
    }
}

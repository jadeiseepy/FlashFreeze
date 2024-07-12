package me.basiqueevangelist.flashfreeze.client;

import eu.pb4.polymer.networking.api.client.PolymerClientNetworking;
import me.basiqueevangelist.flashfreeze.FlashFreeze;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.nbt.NbtInt;

public class FlashFreezeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PolymerClientNetworking.setClientMetadata(FlashFreeze.NETWORK_VERSION_ID, NbtInt.of(FlashFreeze.NETWORK_VERISON));
    }
}

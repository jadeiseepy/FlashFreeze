package me.basiqueevangelist.flashfreeze.testmod;

import com.mojang.serialization.Codec;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class FlashFreezeTestMod implements ModInitializer {
    public static final ComponentKey<TestComponent> TEST_COMPONENT = ComponentRegistry.getOrCreate(Identifier.of("flashfreeze:test"), TestComponent.class);
    public static final ComponentType<Integer> TEST_DATA_COMPONENT = ComponentType.<Integer>builder()
        .codec(Codec.INT)
        .packetCodec(PacketCodecs.INTEGER)
        .build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of("flashfreeze:test"), TEST_DATA_COMPONENT);
    }
}

package me.basiqueevangelist.flashfreeze.item;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record UnknownDataComponents(Map<Identifier, Optional<NbtCompound>> components) {
    public static final PacketCodec<RegistryByteBuf, UnknownDataComponents> PACKET_CODEC = PacketCodecs.<ByteBuf, Identifier, Optional<NbtCompound>, Map<Identifier, Optional<NbtCompound>>>map(
        HashMap::new,
        Identifier.PACKET_CODEC,
        PacketCodecs.optional(PacketCodecs.NBT_COMPOUND)
    ).xmap(UnknownDataComponents::new, UnknownDataComponents::components).cast();
}

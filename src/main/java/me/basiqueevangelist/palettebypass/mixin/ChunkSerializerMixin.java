package me.basiqueevangelist.palettebypass.mixin;

import me.basiqueevangelist.palettebypass.FakeWorldChunk;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ChunkSerializer;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {
    @Redirect(method = "loadEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;getBoolean(Ljava/lang/String;)Z"))
    private static boolean dontLoadIfUnknown(NbtCompound tag, String name) {
        if (tag.contains("id", NbtElement.STRING_TYPE)) {
            if (!Registry.BLOCK_ENTITY_TYPE.containsId(new Identifier(tag.getString("id"))))
                return true;
        }

        return tag.getBoolean(name);
    }

    @Inject(method = "serialize", at = @At("HEAD"), cancellable = true)
    private static void skipIfFake(ServerWorld world, Chunk chunk, CallbackInfoReturnable<NbtCompound> cir) {
        if (chunk instanceof FakeWorldChunk)
            cir.setReturnValue(((FakeWorldChunk) chunk).getUpdatedTag());
    }
}

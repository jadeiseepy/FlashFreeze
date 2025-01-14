package me.basiqueevangelist.flashfreeze.mixin;

import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import me.basiqueevangelist.flashfreeze.components.ComponentHolder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.gen.GeneratorOptions;
import net.minecraft.world.level.LevelInfo;
import net.minecraft.world.level.LevelProperties;
import net.minecraft.world.level.storage.SaveVersionInfo;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {
    @Unique private final ComponentHolder componentHolder = new ComponentHolder();

    @Inject(method = "readProperties", at = @At("RETURN"))
    private static void readCCAComponents(Dynamic<?> dynamic, LevelInfo info, LevelProperties.SpecialProperty specialProperty, GeneratorOptions generatorOptions, Lifecycle lifecycle, CallbackInfoReturnable<LevelProperties> cir) {
        if (FabricLoader.getInstance().isModLoaded("cardinal-components-level")) return;

        ((LevelPropertiesMixin)(Object) cir.getReturnValue()).componentHolder.fromTag((NbtCompound) dynamic.getValue());
    }

    @Inject(method = "updateProperties", at = @At("RETURN"))
    private void writeCCAComponents(DynamicRegistryManager registryManager, NbtCompound levelTag, NbtCompound playerTag, CallbackInfo ci) {
        if (FabricLoader.getInstance().isModLoaded("cardinal-components-level")) return;

        componentHolder.toTag(levelTag);
    }
}

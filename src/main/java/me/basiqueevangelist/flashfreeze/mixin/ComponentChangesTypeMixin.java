package me.basiqueevangelist.flashfreeze.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import me.basiqueevangelist.flashfreeze.access.ComponentChangesTypeAccess;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComponentChanges.Type.class)
public class ComponentChangesTypeMixin implements ComponentChangesTypeAccess {
    @Shadow @Final private boolean removed;
    @Unique
    private Identifier componentTypeId;

    @Override
    public Identifier flashfreeze$getComponentTypeId() {
        return componentTypeId;
    }

    @Override
    public void flashfreeze$setComponentTypeId(Identifier componentTypeId) {
        this.componentTypeId = componentTypeId;
    }

    @Inject(method = "method_57858", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;get(Lnet/minecraft/util/Identifier;)Ljava/lang/Object;"), cancellable = true)
    private static void decode(String id, CallbackInfoReturnable<DataResult<ComponentChanges.Type>> cir, @Local Identifier componentTypeId, @Local boolean isRemoved) {
        if (!Registries.DATA_COMPONENT_TYPE.containsId(componentTypeId)) {
            var type = new ComponentChanges.Type(null, isRemoved);
            ((ComponentChangesTypeMixin)(Object) type).componentTypeId = componentTypeId;
            cir.setReturnValue(DataResult.success(type));
        }
    }

    @WrapOperation(method = "method_57859", at = @At(value = "INVOKE", target = "Lnet/minecraft/registry/Registry;getId(Ljava/lang/Object;)Lnet/minecraft/util/Identifier;"))
    private static @Nullable Identifier encode(Registry<ComponentType<?>> instance, /*ComponentType<?>*/ Object componentType, Operation<Identifier> original, ComponentChanges.Type changesType) {
        var componentTypeId = ((ComponentChangesTypeAccess)(Object) changesType).flashfreeze$getComponentTypeId();
        if (componentTypeId != null) {
            return componentTypeId;
        } else {
            return original.call(instance, componentType);
        }
    }

    @Inject(method = "getValueCodec", at = @At("HEAD"), cancellable = true)
    private void resetValueCodec(CallbackInfoReturnable<Codec<?>> cir) {
        if (componentTypeId != null)
            cir.setReturnValue(this.removed ? Codec.EMPTY.codec() : NbtCompound.CODEC);
    }
}

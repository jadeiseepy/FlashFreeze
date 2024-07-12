package me.basiqueevangelist.flashfreeze.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.basiqueevangelist.flashfreeze.item.FlashFreezeDataComponents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentMapImpl;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

public record SerializedItemStack(Identifier id, int count, ComponentChanges components) {
    public static final Codec<SerializedItemStack> CODEC = Codec.lazyInitialized(
        () -> RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("id").forGetter(SerializedItemStack::id),
                    Codecs.POSITIVE_INT.fieldOf("count").orElse(1).forGetter(SerializedItemStack::count),
                    ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(SerializedItemStack::components)
                )
                .apply(instance, SerializedItemStack::new)
        )
    );
    public static final Codec<SerializedItemStack> UNCOUNTED_CODEC = Codec.lazyInitialized(
        () -> RecordCodecBuilder.create(
            instance -> instance.group(
                    Identifier.CODEC.fieldOf("id").forGetter(SerializedItemStack::id),
                    ComponentChanges.CODEC.optionalFieldOf("components", ComponentChanges.EMPTY).forGetter(SerializedItemStack::components)
                )
                .apply(instance, (item, components) -> new SerializedItemStack(item, 1, components))
        )
    );

    public static SerializedItemStack from(ItemStack stack) {
        Identifier itemId = stack.getOrDefault(FlashFreezeDataComponents.ORIGINAL_ITEM_ID, Registries.ITEM.getId(stack.getItem()));
        ComponentChanges changes;

        if (stack.contains(FlashFreezeDataComponents.ORIGINAL_ITEM_ID)) {
            var copiedComponents = ((ComponentMapImpl) stack.getComponents()).copy();
            copiedComponents.remove(FlashFreezeDataComponents.ORIGINAL_ITEM_ID);
            changes = copiedComponents.getChanges();
        } else {
            changes = stack.getComponentChanges();
        }

        return new SerializedItemStack(itemId, stack.getCount(), changes);
    }

    public DataResult<SerializedItemStack> validateUnknown() {
        if (Registries.ITEM.containsId(id()))
            return DataResult.error(() -> "Item '" + id + "' is present in registry");

        return DataResult.success(this);
    }
}

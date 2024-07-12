package me.basiqueevangelist.flashfreeze.testmod;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;

public class TestComponentImpl implements TestComponent {
    private int value = 0;

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        value = tag.getInt("Value");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup registryLookup) {
        tag.putInt("Value", value);
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }
}

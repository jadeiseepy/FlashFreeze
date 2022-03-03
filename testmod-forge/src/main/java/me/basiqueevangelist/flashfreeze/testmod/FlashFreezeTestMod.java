package me.basiqueevangelist.flashfreeze.testmod;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(FlashFreezeTestMod.MODID)
@Mod.EventBusSubscriber(modid = FlashFreezeTestMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FlashFreezeTestMod {
    public static final String MODID = "flashfreeze_testmod";
    public static final Capability<TestComponent> TEST_COMPONENT = CapabilityManager.get(new CapabilityToken<>() {});

    public FlashFreezeTestMod() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(TestComponent.class);
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEntity(AttachCapabilitiesEvent<Entity> event) {
        event.addCapability(new Identifier(MODID, "test"), new TestComponentImpl());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesBlockEntity(AttachCapabilitiesEvent<BlockEntity> event) {
        event.addCapability(new Identifier(MODID, "test"), new TestComponentImpl());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesItem(AttachCapabilitiesEvent<ItemStack> event) {
        event.addCapability(new Identifier(MODID, "test"), new TestComponentImpl());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesWorld(AttachCapabilitiesEvent<World> event) {
        event.addCapability(new Identifier(MODID, "test"), new TestComponentImpl());
    }

    @SubscribeEvent
    public void onAttachCapabilitiesChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(new Identifier(MODID, "test"), new TestComponentImpl());
    }
}
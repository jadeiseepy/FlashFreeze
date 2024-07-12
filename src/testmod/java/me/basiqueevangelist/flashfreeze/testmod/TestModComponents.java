package me.basiqueevangelist.flashfreeze.testmod;

import net.minecraft.entity.LivingEntity;
import org.ladysnake.cca.api.v3.block.BlockComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.block.BlockComponentInitializer;
import org.ladysnake.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.chunk.ChunkComponentInitializer;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.level.LevelComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.level.LevelComponentInitializer;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentInitializer;
import org.ladysnake.cca.api.v3.world.WorldComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.world.WorldComponentInitializer;

public class TestModComponents implements BlockComponentInitializer, ChunkComponentInitializer, EntityComponentInitializer, WorldComponentInitializer, LevelComponentInitializer, ScoreboardComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, FlashFreezeTestMod.TEST_COMPONENT, e -> new TestComponentImpl());
    }

    @Override
    public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
//        registry.register(FlashFreezeTestMod.TEST_COMPONENT, l -> new TestComponentImpl());
    }

    @Override
    public void registerScoreboardComponentFactories(ScoreboardComponentFactoryRegistry registry) {
//        registry.registerScoreboardComponent(FlashFreezeTestMod.TEST_COMPONENT, (scoreboard, server) -> new TestComponentImpl());
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
//        registry.register(FlashFreezeTestMod.TEST_COMPONENT, world -> new TestComponentImpl());
    }

    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
//        registry.registerFor(ChestBlockEntity.class, FlashFreezeTestMod.TEST_COMPONENT, be -> new TestComponentImpl());
    }

    @Override
    public void registerChunkComponentFactories(ChunkComponentFactoryRegistry registry) {
//        registry.register(FlashFreezeTestMod.TEST_COMPONENT, chunk -> new TestComponentImpl());
    }
}

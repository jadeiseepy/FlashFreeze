package me.basiqueevangelist.flashfreeze.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import eu.pb4.polymer.core.api.utils.PolymerClientDecoded;
import eu.pb4.polymer.core.api.utils.PolymerKeepModel;
import me.basiqueevangelist.flashfreeze.FlashFreeze;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.network.ServerPlayerEntity;

public class UnknownBlockBlock extends Block implements PolymerBlock, PolymerKeepModel, PolymerClientDecoded {
    public UnknownBlockBlock() {
        super(Settings.copy(Blocks.BEDROCK));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state) {
        return Blocks.BEDROCK.getDefaultState();
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, ServerPlayerEntity player) {
        if (player != null && FlashFreeze.hasAtLeast(player, 1)) {
            return this.getDefaultState();
        }

        return Blocks.BEDROCK.getDefaultState();
    }
}

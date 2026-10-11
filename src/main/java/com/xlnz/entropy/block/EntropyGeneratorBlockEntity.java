package com.xlnz.entropy.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EntropyGeneratorBlockEntity extends BlockEntity {
    public EntropyGeneratorBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ENTROPY_GENERATOR.get(), pos, blockState);
    }
}

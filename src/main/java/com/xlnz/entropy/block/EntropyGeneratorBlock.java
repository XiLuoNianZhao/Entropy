package com.xlnz.entropy.block;

import com.xlnz.entropy.entropy.EntropyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class EntropyGeneratorBlock extends Block implements EntityBlock {
    public EntropyGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide()) {
            // 服务端权威：只有服务端改数据；数据从"本存档唯一的那份"取，而非静态变量。
            EntropyStorage.get(level.getServer()).addWorldEntropySpeed(1.0);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EntropyGeneratorBlockEntity(pos, state);
    }
}

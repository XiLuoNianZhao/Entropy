package com.xlnz.entropy.datagen;

import com.xlnz.entropy.Entropy;
import com.xlnz.entropy.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStatesProvider extends BlockStateProvider {
    public ModBlockStatesProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Entropy.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.ENTROPY_BLOCK.get(), cubeAll(ModBlocks.ENTROPY_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.ENTROPY_GENERATOR.get(), cubeAll(ModBlocks.ENTROPY_GENERATOR.get()));
    }
}

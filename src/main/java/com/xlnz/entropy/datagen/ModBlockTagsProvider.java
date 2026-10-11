package com.xlnz.entropy.datagen;

import com.xlnz.entropy.Entropy;
import com.xlnz.entropy.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Entropy.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENTROPY_BLOCK.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.ENTROPY_BLOCK.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ENTROPY_GENERATOR.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.ENTROPY_GENERATOR.get());
    }
}

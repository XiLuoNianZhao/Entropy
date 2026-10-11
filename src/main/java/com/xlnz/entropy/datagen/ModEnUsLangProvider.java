package com.xlnz.entropy.datagen;

import com.xlnz.entropy.Entropy;
import com.xlnz.entropy.block.ModBlocks;
import com.xlnz.entropy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, Entropy.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.entropy_tab", "Entropy");
        add(ModItems.ENTROPY.get(),  "Entropy");
        add(ModBlocks.ENTROPY_BLOCK.get(), "Entropy Cube");
        add(ModBlocks.ENTROPY_GENERATOR.get(), "Entropy Generator");
    }
}

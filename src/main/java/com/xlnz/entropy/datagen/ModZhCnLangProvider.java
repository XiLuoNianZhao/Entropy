package com.xlnz.entropy.datagen;

import com.xlnz.entropy.Entropy;
import com.xlnz.entropy.block.ModBlocks;
import com.xlnz.entropy.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModZhCnLangProvider extends LanguageProvider {
    public ModZhCnLangProvider(PackOutput output) {
        super(output, Entropy.MOD_ID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.entropy_tab", "熵");
        add(ModItems.ENTROPY.get(),  "熵");
        add(ModBlocks.ENTROPY_BLOCK.get(), "熵立方");
        add(ModBlocks.ENTROPY_GENERATOR.get(), "熵发生器");
    }
}

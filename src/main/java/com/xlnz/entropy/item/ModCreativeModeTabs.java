package com.xlnz.entropy.item;

import com.xlnz.entropy.Entropy;
import com.xlnz.entropy.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.lang.reflect.Parameter;
import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Entropy.MOD_ID);
    
    public static final Supplier<CreativeModeTab> ENTROPY_TAB = CREATIVE_MODE_TABS.register("entropy_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ENTROPY.get()))
            .title(Component.translatable("itemGroup.entropy_tab"))
            .displayItems((parameter, output) -> {
                output.accept(ModItems.ENTROPY);
                output.accept(ModBlocks.ENTROPY_BLOCK);
                output.accept(ModBlocks.ENTROPY_GENERATOR);
            }).build());
    
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

package com.xlnz.entropy.item;

import com.xlnz.entropy.Entropy;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Entropy.MOD_ID);
    
    public static final DeferredItem<Item> ENTROPY = ITEMS.register("entropy",
            () -> new Item(new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

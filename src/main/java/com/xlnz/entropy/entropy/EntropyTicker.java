package com.xlnz.entropy.entropy;

import com.xlnz.entropy.Entropy;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = Entropy.MOD_ID)
public class EntropyTicker {
    private static int ticker = 0;
    
    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        ticker++;
        if (ticker >= 20) {
            ticker = 0;
            EntropyStorage.addWorldEntropy(EntropyStorage.getWorldEntropySpeed());
        }
    }
}

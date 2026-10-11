package com.xlnz.entropy.entropy;

import com.xlnz.entropy.Entropy;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

/**
 * 服务器 tick 驱动：每 20 tick（1 秒）把"当前熵速"加到"世界熵"上。
 *
 * <p>改造点：数据不再来自静态变量，而是每次从服务端取"本存档唯一的那份"
 * {@link EntropyStorage}，因此读档后熵与熵速都能延续。</p>
 */
@EventBusSubscriber(modid = Entropy.MOD_ID)
public class EntropyTicker {
    /** 20 tick = 1 秒。 */
    private static final int TICKS_PER_SECOND = 20;

    private static int ticker = 0;

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        ticker++;
        if (ticker >= TICKS_PER_SECOND) {
            ticker = 0;
            MinecraftServer server = event.getServer();
            EntropyStorage storage = EntropyStorage.get(server);
            storage.addWorldEntropy(storage.getWorldEntropySpeed());
        }
    }
}

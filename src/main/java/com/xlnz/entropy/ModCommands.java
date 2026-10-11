package com.xlnz.entropy;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.xlnz.entropy.entropy.EntropyStorage;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = Entropy.MOD_ID)
public class ModCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("entropy")
                        .executes(ctx -> {
                            EntropyStorage storage = storage(ctx.getSource());
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal("当前熵: " + storage.getWorldEntropy()), false
                            );
                            return 1;
                        })
                        .then(Commands.literal("add")
                                .then(Commands.argument("value", DoubleArgumentType.doubleArg())
                                        .executes(ctx -> {
                                            double value = DoubleArgumentType.getDouble(ctx, "value");
                                            storage(ctx.getSource()).addWorldEntropy(value);
                                            ctx.getSource().sendSuccess(
                                                    () -> Component.literal("已增加 " + value + "熵"), false
                                            );
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("speed")
                                .executes(ctx -> {
                                    EntropyStorage storage = storage(ctx.getSource());
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal("世界熵增加速度: " + storage.getWorldEntropySpeed()), false
                                    );
                                    return 1;
                                })
                        )
        
        );
    }

    /** 从命令来源拿服务端，再取"本存档唯一的那份"熵数据。 */
    private static EntropyStorage storage(CommandSourceStack source) {
        return EntropyStorage.get(source.getServer());
    }
}

package com.xlnz.entropy;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.xlnz.entropy.entropy.EntropyStorage;
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
                            ctx.getSource().sendSuccess(
                                    () -> Component.literal("当前熵: " + EntropyStorage.getWorldEntropy()), false
                            );
                            return 1;
                        })
                        .then(Commands.literal("add")
                                .then(Commands.argument("value", DoubleArgumentType.doubleArg())
                                        .executes(ctx -> {
                                            double value = DoubleArgumentType.getDouble(ctx, "value");
                                            EntropyStorage.addWorldEntropy(value);
                                            ctx.getSource().sendSuccess(
                                                    () -> Component.literal("已增加 " + value + "熵"), false
                                            );
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("speed")
                                .executes(ctx -> {
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal("世界熵增加速度: " + EntropyStorage.getWorldEntropySpeed()), false
                                    );
                                    return 1;
                                })
                        )
        
        );
    }
}

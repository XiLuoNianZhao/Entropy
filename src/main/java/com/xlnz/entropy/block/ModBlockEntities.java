package com.xlnz.entropy.block;

import com.xlnz.entropy.Entropy;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * 方块实体类型（BlockEntityType）的注册中心。
 *
 * <p>方块（Block）只描述"世界里的一块方砖长什么样、被点击时发生什么"；
 * 需要保存额外数据（进度、能量、熵值……）的方块，必须配套一个"方块实体"（BlockEntity），
 * 它才是真正挂在某个坐标上、能存数据、能被 tick 的对象。</p>
 *
 * <p>而"坐标上该创建哪个 BlockEntity"这件事由 BlockEntityType 决定，
 * 所以每个 BlockEntity 都必须在这里登记一次，否则游戏在放置方块时会找不到数据对象。</p>
 */
public class ModBlockEntities {
    /**
     * 方块实体类型的延迟注册表。
     * DeferredRegister = "先登记名字和构造方式，等游戏进入注册阶段再统一提交"，避免过早创建对象。
     */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Entropy.MOD_ID);

    /**
     * entropy_generator 方块对应的方块实体类型。
     *
     * <p>{@code Builder.of(EntropyGeneratorBlockEntity::new, ModBlocks.ENTROPY_GENERATOR.get())} 的含义：
     * 第一个参数是"怎么创建这个数据对象"（构造方法引用），
     * 后面可变参数是"这个类型允许挂在哪些方块上"。</p>
     *
     * <p>{@code build(null)} 里的 null 是旧版数据修复器（DataFixer）参数，本地模组用不到，传 null 即可。</p>
     */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EntropyGeneratorBlockEntity>> ENTROPY_GENERATOR =
            BLOCK_ENTITIES.register("entropy_generator",
                    () -> BlockEntityType.Builder.of(EntropyGeneratorBlockEntity::new, ModBlocks.ENTROPY_GENERATOR.get()).build(null));

    /** 把上面的注册表挂到模组事件总线上，Entropy 主类会调用它。 */
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

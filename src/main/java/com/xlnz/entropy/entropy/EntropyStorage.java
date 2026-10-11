package com.xlnz.entropy.entropy;

import com.xlnz.entropy.Entropy;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * 世界熵数据：**全局标量，每个存档只有一份**，统一挂在主世界（Overworld）的数据存储上。
 *
 * <p>与改造前的区别：以前是两个 {@code static double}，进程一退出就归零、多个存档还会互相串；
 * 现在继承 {@link SavedData}，游戏会在存档目录的 {@code data/} 下写一个
 * {@code entropy_world_entropy.dat}，读档时自动读回来。</p>
 *
 * <p>"世界统一"的实现要点：{@link #get(MinecraftServer)} 永远走 {@code server.overworld()}，
 * 所以无论从主世界、下界还是末地调用，拿到的都是同一份数据，不会按维度各存一份。</p>
 */
public class EntropyStorage extends SavedData {
    /** 存档里的数据名，最终落盘为 data/entropy_world_entropy.dat。 */
    public static final String DATA_NAME = Entropy.MOD_ID + "_world_entropy";

    /** NBT 标签键名（存档文件里的字段名）。 */
    private static final String KEY_ENTROPY = "WorldEntropy";
    private static final String KEY_SPEED = "WorldEntropySpeed";

    /** 世界熵总量（无上限）。 */
    private double worldEntropy = 0;
    /** 世界熵增长速度（每秒增加多少熵），初始为 1。 */
    private double worldEntropySpeed = 1;

    /**
     * 取当前存档唯一的那份熵数据；不存在时游戏会用第一个参数（构造方法）新建一份。
     *
     * <p>{@code server.overworld().getDataStorage()} 是关键：它拿的是主世界维度的数据存储，
     * 因此本数据是"整档一份"，而不是"每维度一份"。</p>
     */
    public static EntropyStorage get(MinecraftServer server) {
        return server.overworld().getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(EntropyStorage::new, EntropyStorage::load), DATA_NAME);
    }

    /** 读档：从 NBT 还原（第二个参数是注册表查询器，本数据用不到）。 */
    public static EntropyStorage load(CompoundTag tag, HolderLookup.Provider registries) {
        EntropyStorage data = new EntropyStorage();
        data.worldEntropy = tag.getDouble(KEY_ENTROPY);
        if (tag.contains(KEY_SPEED)) {
            data.worldEntropySpeed = tag.getDouble(KEY_SPEED);
        }
        return data;
    }

    /** 存档：把当前值写进 NBT。 */
    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putDouble(KEY_ENTROPY, worldEntropy);
        tag.putDouble(KEY_SPEED, worldEntropySpeed);
        return tag;
    }

    public double getWorldEntropy() {
        return worldEntropy;
    }

    public void setWorldEntropy(double value) {
        worldEntropy = value;
        setDirty(); // "数据脏了"：告诉游戏下次写存档时要把它落盘
    }

    public void addWorldEntropy(double amount) {
        worldEntropy += amount;
        setDirty();
    }

    public double getWorldEntropySpeed() {
        return worldEntropySpeed;
    }

    public void setWorldEntropySpeed(double value) {
        worldEntropySpeed = value;
        setDirty();
    }

    public void addWorldEntropySpeed(double amount) {
        worldEntropySpeed += amount;
        setDirty();
    }
}

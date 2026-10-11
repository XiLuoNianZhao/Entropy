package com.xlnz.entropy.entropy;

public class EntropyStorage {
    private static double WorldEntropy;
    private static double WorldEntropySpeed = 1;
    
    public static double getWorldEntropy() {
        return WorldEntropy;
    }
    
    public static void setWorldEntropy(double WorldEntropy) {
        EntropyStorage.WorldEntropy = WorldEntropy;
    }
    
    public static void addWorldEntropy(double WorldEntropy) {
        EntropyStorage.WorldEntropy += WorldEntropy;
    }
    
    public static double getWorldEntropySpeed() {
        return WorldEntropySpeed;
    }
    
    public static void setWorldEntropySpeed(double WorldEntropySpeed) {
        EntropyStorage.WorldEntropySpeed = WorldEntropySpeed;
    }
    
    public static void addWorldEntropySpeed(double WorldEntropySpeed) {
        EntropyStorage.WorldEntropySpeed += WorldEntropySpeed;
    }
}

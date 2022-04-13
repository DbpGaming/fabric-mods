package net.dbp.basic_ores.ore_api;

import java.util.function.Predicate;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;

public class OreGeneration {
    public String name;
    public OreType[] oretypes;
    public int max;
    public int min;
    public int weight;
    public int size;
    public float discard;
    public Predicate<BiomeSelectionContext> biome;

    public OreGeneration(String name, Predicate<BiomeSelectionContext> biome, int min, int max, int weight, int size, float discard, OreType... oretypes){
        this.name = name;
        this.oretypes = oretypes;
        this.biome = biome;
        this.min = min;
        this.max = max;
        this.weight = weight;
        this.size = size;
        this.discard = discard;
    }
}

package net.dbp.basic_ores.ore_api;

import java.util.function.Predicate;
import net.fabricmc.fabric.api.biome.v1.*;
import net.minecraft.world.biome.Biome;

public class BiomeSelection {
    public static Predicate<BiomeSelectionContext> OVERWORLD = BiomeSelectors.foundInOverworld();
	public static Predicate<BiomeSelectionContext> NETHER = BiomeSelectors.foundInTheNether();
	public static Predicate<BiomeSelectionContext> END = BiomeSelectors.foundInTheEnd();
	public static Predicate<BiomeSelectionContext> DESERT = BiomeSelectors.categories(Biome.Category.DESERT);
	public static Predicate<BiomeSelectionContext> ICY = BiomeSelectors.categories(Biome.Category.ICY);
	public static Predicate<BiomeSelectionContext> JUNGLE = BiomeSelectors.categories(Biome.Category.JUNGLE);
	public static Predicate<BiomeSelectionContext> MUSHROOM = BiomeSelectors.categories(Biome.Category.MUSHROOM);
	public static Predicate<BiomeSelectionContext> EXTREME = BiomeSelectors.categories(Biome.Category.EXTREME_HILLS);
	public static Predicate<BiomeSelectionContext> OCEAN = BiomeSelectors.categories(Biome.Category.OCEAN);
	public static Predicate<BiomeSelectionContext> PLAINS = BiomeSelectors.categories(Biome.Category.PLAINS);
}


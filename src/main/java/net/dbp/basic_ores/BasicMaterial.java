package net.dbp.basic_ores;

import java.util.*;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
//import draylar.gateofbabylon.item.*;
import draylar.magna.item.*;
import net.dbp.basic_ores.tool_api.*;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.*;

public class BasicMaterial {
    public final HashMap<String, Item> itemParts = new HashMap<>();
	public final HashMap<String, Block> blockPartsBlocks = new HashMap<>();
	public final HashMap<String, Item> blockPartsItems = new HashMap<>();
	public final HashSet<String> tags = new HashSet<>();
	public final String name;
	public int color = 0xFFFFFF;
	public int magicNumber = 2;
	public int magicNumber2 = 1;

    public BasicMaterial(String name, int color) {
		this.name = name;
		this.color = color;
	}

	public Ingredient getRepair() {
		if (itemParts.containsKey("ingot")){
			return Ingredient.ofItems(itemParts.get("ingot"));
		}else if(itemParts.containsKey("gem")){
			return Ingredient.ofItems(itemParts.get("gem"));
		}else{
			return null;
		}
	}

	public class ToolMat implements ToolMaterial{
		@Override
		public int getDurability() {
			return magicNumber2*magicNumber2*magicNumber2*60;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return magicNumber*3;
		}

		@Override
		public float getAttackDamage() {
			return magicNumber;
		}

		@Override
		public int getMiningLevel() {
			return magicNumber;
		}

		@Override
		public int getEnchantability() {
			return magicNumber2*18;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return getRepair();
		}

	}

	public class ArmorMat implements ArmorMaterial{
		@Override
		public int getDurability(EquipmentSlot slot) {
			int[] dura = new int[] {magicNumber2*12, magicNumber2*14, magicNumber2*16, magicNumber2*10};
			return dura[slot.getEntitySlotId()];
		}

		@Override
		public int getProtectionAmount(EquipmentSlot slot) {
			int[] prot = new int[] {magicNumber, magicNumber*2+1, magicNumber*2+2, magicNumber};
			return prot[slot.getEntitySlotId()];
		}

		@Override
		public int getEnchantability() {
			return magicNumber2*10;
		}

		@Override
		public SoundEvent getEquipSound() {
			return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return getRepair();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public float getToughness() {
			if (magicNumber >= 3){
				return magicNumber;
			}else{
				return 0;
			}
		}

		@Override
		public float getKnockbackResistance() {
			if (magicNumber >= 3){
				return magicNumber/30;
			}else{
				return 0;
			}
		}
		
	}

	public BasicMaterial setMagicNumber(int magicNumber, int magicNumber2){
		this.magicNumber = magicNumber;
		this.magicNumber2 = magicNumber2;
		return this;
	}

    public BasicMaterial addItemParts(String[]... partArrays) {
		for (String[] parts : partArrays) {
			for (String part : parts) {
				switch (part) {
				case "hammer":
					this.itemParts.put(part, new HammerItem(new ToolMat(), 1, -3.0f, new Item.Settings().group(ItemGroup.MISC).recipeRemainder(Items.IRON_AXE)));
				break;
				case "excavator":
					this.itemParts.put(part, new ExcavatorItem(new ToolMat(), 1, -3.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "axe":
					this.itemParts.put(part, new Axe(new ToolMat(), 6, -3.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "hoe":
					this.itemParts.put(part, new Hoe(new ToolMat(), 0, -3.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "pickaxe":
					this.itemParts.put(part, new Pickaxe(new ToolMat(), 1, -2.8f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "shovel":
					this.itemParts.put(part, new Shovel(new ToolMat(), 1, -3.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "sword":
					this.itemParts.put(part, new Sword(new ToolMat(), 3, -2.4f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "shear":
					if (Basic.moreTagsCompat || Basic.bclibCompat) this.itemParts.put(part, new Shear(new Item.Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				break;
				case "fishingrod":
					this.itemParts.put(part, new FishingRodItem(new Item.Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				break;
				case "bow":
					this.itemParts.put(part, new BowItem(new Item.Settings().group(ItemGroup.MISC).maxDamage(magicNumber2*magicNumber2*magicNumber2*60)));
				break;
				case "shield":
					itemParts.put(part, new FabricShieldItem(new Item.Settings().maxDamage(magicNumber2*magicNumber2*magicNumber2*60).group(ItemGroup.MISC), 10, 13, itemParts.get("ingot")));
				break;
				case "helmet":
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "chestplate":
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "legging":
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "boot":
					this.itemParts.put(part, new ArmorItem(new ArmorMat(), EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "paxel":
					this.itemParts.put(part, new Paxel(new ToolMat(), 1, -2.8f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "dagger":
					//this.itemParts.put(part, new DaggerItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "spear":
					//this.itemParts.put(part, new SpearItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "broadsword":
					//this.itemParts.put(part, new BroadswordItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "rapier":
					//this.itemParts.put(part, new RapierItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "haladie":
					//this.itemParts.put(part, new HaladieItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "waraxe":
					//this.itemParts.put(part, new WaraxeItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "katana":
					//this.itemParts.put(part, new KatanaItem(new ToolMat(), 0.5f, -1.0f, new Item.Settings().group(ItemGroup.MISC)));
				break;
				case "boomerang":
					//this.itemParts.put(part, new BoomerangItem(new Item.Settings().group(ItemGroup.MISC), new ToolMat()));
				break;
				default:
					this.itemParts.put(part, new Item(new Item.Settings().group(ItemGroup.MISC)));
				break;
				}
			}
		}
		return this;
	}

	public BasicMaterial addItemPart(String... parts) {
		return addItemParts(parts);
	}

	public BasicMaterial addBlockParts(String[]... partArrays) {
		for (String[] parts : partArrays) {
			for (String part : parts) {
				this.blockPartsBlocks.put(part, new Block(Block.Settings.of(Material.STONE).strength(4.0f)));
				this.blockPartsItems.put(part, new BlockItem(this.blockPartsBlocks.get(part), new Item.Settings().group(ItemGroup.MISC)));
			}
		}
		return this;
	}

	public BasicMaterial addBlockPart(String... parts) {
		return addBlockParts(parts);
	}


	public BasicMaterial addTags(String[]... tagArrays) {
		for (String[] tags : tagArrays) this.tags.addAll(Arrays.asList(tags));
		return this;
	}

	public BasicMaterial addTag(String... tags) {
		return addTags(tags);
	}
}

package net.nml.windowtoreality;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.nml.windowtoreality.block.CutoutBlock;
import net.nml.windowtoreality.block.CutoutBlockEntity;

import java.util.Set;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("null")
public class WindowToReality implements ModInitializer {
	public static final String MOD_ID = "window-to-reality";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final Block cutoutBlock = block("cutout_block", CutoutBlock::new, BlockBehaviour.Properties.of()
			.strength(-1.0F, 3600000.8F)
			.mapColor(MapColor.NONE)
			.noLootTable()
			.noOcclusion()
			.isValidSpawn((a, b, c, d) -> false)
			.noTerrainParticles()
			.pushReaction(PushReaction.BLOCK));
	public static final BlockEntityType<CutoutBlockEntity> cutoutBlockEntity = blockEntityType("cutout_block", CutoutBlockEntity::new, cutoutBlock);


	private static <T> ResourceKey<T> key(Registry<T> registry, String name) {
		return ResourceKey.create(registry.key(), of(name));
	}
	private static Item blockItem(String name, Block block, Item.Properties properties) {
		return item(name, s -> new BlockItem(block, s), properties.setId(key(BuiltInRegistries.ITEM, name)).useBlockDescriptionPrefix());
	}
	private static Item item(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = key(BuiltInRegistries.ITEM, name);
		Item item = (Item)factory.apply(properties.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}

		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}
	private static Block block(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> blockKey = key(BuiltInRegistries.BLOCK, name);
		Block block = blockFactory.apply(properties.setId(blockKey));
		blockItem(name, block, new Item.Properties());
		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}
	private static <T extends BlockEntity> BlockEntityType<T> blockEntityType(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntityFactory, Block block) {
	  return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, of(name), new BlockEntityType<>(blockEntityFactory, Set.of(block)));
	}

	public static Identifier of(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
	}
}
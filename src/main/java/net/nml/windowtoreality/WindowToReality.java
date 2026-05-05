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

	public static final Block cutoutBlock = block("cutout", CutoutBlock::new, cutoutProperties());
	public static final Block cutoutBarrier = block("cutout_barrier", CutoutBlock::new, cutoutProperties().strength(-1.0F, 3600000.8F));
	public static final Block cutoutWindow = block("cutout_window", CutoutBlock::new, cutoutProperties().noCollision());
	public static final Block cutoutPortal = block("cutout_portal", CutoutBlock::new, cutoutProperties().strength(-1.0F, 3600000.8F).noCollision());
	public static final BlockEntityType<CutoutBlockEntity> cutoutBlockEntity = blockEntityType("cutout_block", CutoutBlockEntity::new, Set.of(cutoutBlock, cutoutBarrier, cutoutWindow, cutoutPortal));

	public static final boolean CLEAR_SKY = booleanProperty("WTR_CLEAR_SKY");

	private static boolean booleanProperty(final String name) {
		String value = System.getProperty(name);
		return value != null && (value.isEmpty() || Boolean.parseBoolean(value));
	}
	
	//#region helpers
	private static <T> ResourceKey<T> key(Registry<T> registry, String name) {
		return ResourceKey.create(registry.key(), of(name));
	}
	private static Item item(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = key(BuiltInRegistries.ITEM, name);
		Item item = (Item)factory.apply(properties.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}
	private static Item blockItem(String name, Block block, Item.Properties properties) {
		return item(name, s -> new BlockItem(block, s), properties.setId(key(BuiltInRegistries.ITEM, name)).useBlockDescriptionPrefix());
	}
	private static Block block(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> blockKey = key(BuiltInRegistries.BLOCK, name);
		Block block = blockFactory.apply(properties.setId(blockKey));
		blockItem(name, block, new Item.Properties());
		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}
	private static BlockBehaviour.Properties cutoutProperties() {
		return BlockBehaviour.Properties.of()
			.strength(1.5F, 6.0F)
			.mapColor(MapColor.NONE)
			.noOcclusion()
			.isViewBlocking((a, b, c) -> true)
			.isValidSpawn((a, b, c, d) -> false)
			.noTerrainParticles()
			.pushReaction(PushReaction.BLOCK);
	}
	private static <T extends BlockEntity> BlockEntityType<T> blockEntityType(String name, BlockEntityType.BlockEntitySupplier<? extends T> blockEntityFactory, Set<Block> blocks) {
	  return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, of(name), new BlockEntityType<>(blockEntityFactory, blocks));
	}
	//#endregion

	public static Identifier of(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
	}
}
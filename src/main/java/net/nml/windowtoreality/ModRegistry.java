package net.nml.windowtoreality;

import java.util.function.Function;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.nml.windowtoreality.block.CutoutBlock;
import net.nml.windowtoreality.block.CutoutBlockEntity;

@SuppressWarnings("null")
public class ModRegistry {
	public static final Block cutoutBlock = block("cutout_block", CutoutBlock::new, cutoutProperties());
	public static final Block cutoutBarrier = block("cutout_barrier", CutoutBlock::new, cutoutProperties().strength(-1.0F, 3600000.8F));
	public static final Block cutoutWindow = block("cutout_window", CutoutBlock::new, cutoutProperties().noCollision());
	public static final Block cutoutVoid = block("cutout_void", CutoutBlock::new, cutoutProperties().strength(-1.0F, 3600000.8F).noCollision());
	public static final TagKey<Block> cutoutBlocksTag = TagKey.create(Registries.BLOCK, WindowToReality.of("cutout_blocks"));
	public static final BlockEntityType<CutoutBlockEntity> cutoutBlockEntity = blockEntityType("cutout_block", CutoutBlockEntity::new, cutoutBlock, cutoutBarrier, cutoutWindow, cutoutVoid);
	
	static BlockBehaviour.Properties cutoutProperties() {
		return BlockBehaviour.Properties.of()
			.strength(1.5F, 6.0F)
			.mapColor(MapColor.NONE)
			.noOcclusion()
			.isViewBlocking((a, b, c) -> true)
			.isValidSpawn((a, b, c, d) -> false)
			.noTerrainParticles()
			.pushReaction(PushReaction.BLOCK);
	}

	//#region helpers
	static <T> ResourceKey<T> key(Registry<T> registry, String name) {
		return ResourceKey.create(registry.key(), WindowToReality.of(name));
	}
	static Item item(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = key(BuiltInRegistries.ITEM, name);
		Item item = (Item)factory.apply(properties.setId(key));
		if (item instanceof BlockItem blockItem) {
			blockItem.registerBlocks(Item.BY_BLOCK, item);
		}
		return Registry.register(BuiltInRegistries.ITEM, key, item);
	}
	static Item blockItem(String name, Block block, Item.Properties properties) {
		return item(name, s -> new BlockItem(block, s), properties.setId(key(BuiltInRegistries.ITEM, name)).useBlockDescriptionPrefix());
	}
	static Block block(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		ResourceKey<Block> blockKey = key(BuiltInRegistries.BLOCK, name);
		Block block = blockFactory.apply(properties.setId(blockKey));
		blockItem(name, block, new Item.Properties());
		return Registry.register(BuiltInRegistries.BLOCK, blockKey, block);
	}
	static <T extends BlockEntity> BlockEntityType<T> blockEntityType(String name, FabricBlockEntityTypeBuilder.Factory<? extends T> blockEntityFactory, Block... blocks) {
	  return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, WindowToReality.of(name), FabricBlockEntityTypeBuilder.<T>create(blockEntityFactory, blocks).build());
	}
	//#endregion
	
	protected static void init() {
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(output -> {
			output.accept(cutoutBlock);
			output.accept(cutoutBarrier);
			output.accept(cutoutWindow);
			output.accept(cutoutVoid);
		});
	}
}

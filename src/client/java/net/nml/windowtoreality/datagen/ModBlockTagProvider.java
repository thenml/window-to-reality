package net.nml.windowtoreality.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.nml.windowtoreality.ModRegistry;

public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
	protected ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	static ResourceKey<Block> key(Block block) {
		return BuiltInRegistries.BLOCK.getResourceKey(block).orElseThrow();
	}
	
	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		builder(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(key(ModRegistry.cutoutBlock))
			.add(key(ModRegistry.cutoutWindow));
		builder(ModRegistry.cutoutBlocksTag)
			.add(key(ModRegistry.cutoutBlock))
			.add(key(ModRegistry.cutoutBarrier))
			.add(key(ModRegistry.cutoutWindow))
			.add(key(ModRegistry.cutoutVoid));
		builder(BlockTags.DRAGON_IMMUNE)
			.add(key(ModRegistry.cutoutBarrier))
			.add(key(ModRegistry.cutoutVoid));
		builder(BlockTags.WITHER_IMMUNE)
			.add(key(ModRegistry.cutoutBarrier))
			.add(key(ModRegistry.cutoutVoid));
	}
}
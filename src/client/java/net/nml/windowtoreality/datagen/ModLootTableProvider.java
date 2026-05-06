package net.nml.windowtoreality.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.nml.windowtoreality.ModRegistry;

public class ModLootTableProvider extends FabricBlockLootSubProvider {
	protected ModLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		dropSelf(ModRegistry.cutoutBlock);
		dropSelf(ModRegistry.cutoutWindow);
	}
}
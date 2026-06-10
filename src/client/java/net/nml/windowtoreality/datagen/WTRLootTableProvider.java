package net.nml.windowtoreality.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.nml.windowtoreality.WTRRegistry;

public class WTRLootTableProvider extends FabricBlockLootSubProvider {
	protected WTRLootTableProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		dropSelf(WTRRegistry.cutoutBlock);
		dropSelf(WTRRegistry.cutoutWindow);
	}
}
package net.nml.windowtoreality.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WindowToRealityDatagen implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(WTRLootTableProvider::new);
		pack.addProvider(WTRRecipeProvider::new);
		pack.addProvider(WTRModelProvider::new);
		pack.addProvider(WTRBlockTagProvider::new);
	}
}
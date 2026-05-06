package net.nml.windowtoreality.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.nml.windowtoreality.ModRegistry;

public class ModModelProvider extends FabricModelProvider {
	protected ModModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators bsmg) {
	}

	@Override
	public void generateItemModels(ItemModelGenerators img) {
		img.generateFlatItem(ModRegistry.cutoutBlock.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(ModRegistry.cutoutBarrier.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(ModRegistry.cutoutWindow.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(ModRegistry.cutoutVoid.asItem(), ModelTemplates.FLAT_ITEM);
	}
}

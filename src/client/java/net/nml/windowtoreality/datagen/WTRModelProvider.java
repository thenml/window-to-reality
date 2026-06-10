package net.nml.windowtoreality.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.nml.windowtoreality.WTRRegistry;

public class WTRModelProvider extends FabricModelProvider {
	protected WTRModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators bsmg) {
		bsmg.createAirLikeBlock(WTRRegistry.cutoutBlock, WTRRegistry.cutoutBlock.asItem());
		bsmg.createAirLikeBlock(WTRRegistry.cutoutBarrier, WTRRegistry.cutoutBarrier.asItem());
		bsmg.createAirLikeBlock(WTRRegistry.cutoutWindow, WTRRegistry.cutoutWindow.asItem());
		bsmg.createAirLikeBlock(WTRRegistry.cutoutVoid, WTRRegistry.cutoutVoid.asItem());
	}

	@Override
	public void generateItemModels(ItemModelGenerators img) {
		img.generateFlatItem(WTRRegistry.cutoutBlock.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(WTRRegistry.cutoutBarrier.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(WTRRegistry.cutoutWindow.asItem(), ModelTemplates.FLAT_ITEM);
		img.generateFlatItem(WTRRegistry.cutoutVoid.asItem(), ModelTemplates.FLAT_ITEM);
	}
}

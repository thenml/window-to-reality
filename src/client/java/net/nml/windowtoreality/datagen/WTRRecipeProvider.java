package net.nml.windowtoreality.datagen;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.nml.windowtoreality.WTRRegistry;

public class WTRRecipeProvider extends FabricRecipeProvider {
	protected WTRRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
		return new RecipeProvider(registryLookup, output) {
			@Override
			public void buildRecipes() {
				shaped(RecipeCategory.DECORATIONS, WTRRegistry.cutoutBlock, 4)
					.pattern(" X ")
					.pattern("XOX")
					.pattern(" X ")
					.define('X', Items.GLASS)
					.define('O', Items.ENDER_EYE)
					.unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
					.save(output);
				shaped(RecipeCategory.DECORATIONS, WTRRegistry.cutoutWindow, 4)
					.pattern("YXY")
					.pattern("XOX")
					.pattern("YXY")
					.define('X', Items.GLASS)
					.define('Y', Items.GLASS_PANE)
					.define('O', Items.ENDER_EYE)
					.unlockedBy(getHasName(Items.GLASS), has(Items.GLASS))
					.save(output);
			}
		};
	}

	@Override
	public String getName() {
		return "Recipe Provider";
	}
}
package net.nml.windowtoreality.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.nml.windowtoreality.ModRegistry;
import net.nml.windowtoreality.client.renderer.CutoutBlockEntityRenderer;

public class WindowToRealityClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		if (FabricLoader.getInstance().isModLoaded("fabric-api")) {
			ClientRegistry.init();
			BlockEntityRenderers.register(ModRegistry.cutoutBlockEntity, CutoutBlockEntityRenderer::new);
		}
	}
}
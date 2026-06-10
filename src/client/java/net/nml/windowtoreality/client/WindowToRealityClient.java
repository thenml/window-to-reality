package net.nml.windowtoreality.client;

import java.util.Set;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.rendertype.OutputTarget;
import net.minecraft.resources.Identifier;
import net.nml.windowtoreality.WTRRegistry;
import net.nml.windowtoreality.WindowToReality;
import net.nml.windowtoreality.client.renderer.CutoutBlockEntityRenderer;
import net.nml.windowtoreality.client.util.LevelRendererI;

public class WindowToRealityClient implements ClientModInitializer {
	public static final OutputTarget WINDOW_OPACITY_TARGET = new OutputTarget("wtr_window_opacity_target", () -> ((LevelRendererI)Minecraft.getInstance().levelRenderer).windowOpacityTarget());
	public static final Identifier WINDOW_OPACITY_TARGET_ID = WindowToReality.of("opacity");
	public static final Identifier WINDOW_OPACITY_POST_CHAIN_ID = WindowToReality.of("window");
	public static final Set<Identifier> WINDOW_TARGETS = Set.of(LevelTargetBundle.MAIN_TARGET_ID, WINDOW_OPACITY_TARGET_ID);

	@Override
	public void onInitializeClient() {
		if (FabricLoader.getInstance().isModLoaded("fabric-api")) {
			WTRClientRegistry.init();
			BlockEntityRenderers.register(WTRRegistry.cutoutBlockEntity, CutoutBlockEntityRenderer::new);
		}
	}
}
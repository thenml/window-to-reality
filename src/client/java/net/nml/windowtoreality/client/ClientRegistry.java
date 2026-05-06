package net.nml.windowtoreality.client;

import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.nml.windowtoreality.WindowToReality;
import net.nml.windowtoreality.client.mixin.RenderPipelinesAccessor;
import net.nml.windowtoreality.client.mixin.RenderTypeAccessor;

public class ClientRegistry {

	public static final RenderType cutoutRenderType = RenderTypeAccessor.create("window_to_reality_cutout", RenderSetup.builder(RenderPipelinesAccessor.register(
		RenderPipeline.builder()
			.withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
			.withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
			.withBindGroupLayout(BindGroupLayouts.GLOBALS)
			.withVertexShader(WindowToReality.of("cutout"))
			.withFragmentShader(WindowToReality.of("cutout"))
			.withDepthStencilState(DepthStencilState.DEFAULT)
			.withLocation(WindowToReality.of("cutout"))
			.build()
		)).createRenderSetup());
	
	protected static void init() {
		FabricLoader.getInstance().getModContainer(WindowToReality.MOD_ID).ifPresent(modContainer -> {
			ResourceLoader.registerBuiltinPack(WindowToReality.of("clear_sky"), modContainer, Component.translatable("resourcePack.window-to-reality.clear_sky.name"), PackActivationType.NORMAL);
		});
		ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(WindowToReality.of("clear_sky"), new SimplePreparableReloadListener<Boolean>() {
			@Override
			protected Boolean prepare(ResourceManager manager, ProfilerFiller profiler) {
				return manager.getResource(WindowToReality.of("clear_sky.json")).isPresent();
			}

			@Override
			protected void apply(@Nullable Boolean preparations, ResourceManager manager, ProfilerFiller profiler) {
				if (preparations == null) {
					preparations = false;
				}
				WindowToReality.CLEAR_SKY = preparations;
			}
		});
	}
}

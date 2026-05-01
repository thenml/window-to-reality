package net.nml.windowtoreality.client;

import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.BindGroupLayouts;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.nml.windowtoreality.WindowToReality;
import net.nml.windowtoreality.client.mixin.RenderPipelinesAccessor;
import net.nml.windowtoreality.client.mixin.RenderTypeAccessor;

public class WindowToRealityClient implements ClientModInitializer {
	public static final RenderType cutoutRenderType = RenderTypeAccessor.create("window_to_reality_cutout", RenderSetup.builder(RenderPipelinesAccessor.register(
		RenderPipeline.builder(RenderPipeline.builder()
			.withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
			.withBindGroupLayout(BindGroupLayouts.MATRICES_PROJECTION)
			.withBindGroupLayout(BindGroupLayouts.GLOBALS)
			.withVertexShader(WindowToReality.of("cutout"))
			.withFragmentShader(WindowToReality.of("cutout"))
			.withDepthStencilState(DepthStencilState.DEFAULT)
			.buildSnippet()
		).withLocation(WindowToReality.of("cutout")).build())).createRenderSetup());

	@Override
	public void onInitializeClient() {
	}
}
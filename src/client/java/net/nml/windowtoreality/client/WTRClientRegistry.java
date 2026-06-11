package net.nml.windowtoreality.client;

import com.mojang.blaze3d.pipeline.DepthStencilState;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;

import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.nml.windowtoreality.WindowToReality;

public class WTRClientRegistry {
	public static final Snippet cutoutSnippet = RenderPipeline.builder(RenderPipelines.MATRICES_FOG_SNIPPET)
		.withVertexFormat(DefaultVertexFormat.POSITION, VertexFormat.Mode.QUADS)
		.withVertexShader(WindowToReality.of("cutout"))
		.withFragmentShader(WindowToReality.of("cutout"))
		.withDepthStencilState(DepthStencilState.DEFAULT)
		.buildSnippet();

	public static final RenderType cutoutRenderType = RenderType.create("window_to_reality_cutout", RenderSetup.builder(RenderPipelines.register(
		RenderPipeline.builder(cutoutSnippet).withLocation(WindowToReality.of("cutout")).build()
	)).sortOnUpload().createRenderSetup());

	public static final RenderType cutoutMaskRenderType = RenderType.create("window_to_reality_cutout_mask", RenderSetup.builder(RenderPipelines.register(
		RenderPipeline.builder(cutoutSnippet).withLocation(WindowToReality.of("cutout_mask")).build()
	)).setOutputTarget(WindowToRealityClient.WINDOW_OPACITY_TARGET).sortOnUpload().createRenderSetup());

	protected static void init() {}
}

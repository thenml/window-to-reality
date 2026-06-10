package net.nml.windowtoreality.client.mixin;

import org.joml.Matrix4fc;
import org.joml.Vector4f;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.framegraph.FrameGraphBuilder;
import com.mojang.blaze3d.framegraph.FramePass;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.GraphicsResourceAllocator;
import com.mojang.blaze3d.resource.RenderTargetDescriptor;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.client.renderer.ShaderManager;
import net.minecraft.client.renderer.chunk.ChunkSectionsToRender;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import net.minecraft.util.profiling.ProfilerFiller;
import net.nml.windowtoreality.client.WindowToRealityClient;
import net.nml.windowtoreality.client.util.LevelRendererI;
import net.nml.windowtoreality.client.util.LevelTargetBundleI;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements LevelRendererI {
	@Shadow @Final private LevelTargetBundle targets;
	@Shadow @Final private ShaderManager shaderManager;


	@Override @Nullable @Unique
	@SuppressWarnings("null")
	public RenderTarget windowOpacityTarget() {
		return ((LevelTargetBundleI)this.targets).windowOpacity() != null ? ((LevelTargetBundleI)this.targets).windowOpacity().get() : null;
	}


	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;getTransparencyChain()Lnet/minecraft/client/renderer/PostChain;", shift = At.Shift.AFTER))
	private void render$setupRenderTarget(
		final GraphicsResourceAllocator resourceAllocator, final DeltaTracker deltaTracker, final boolean renderOutline, final CameraRenderState cameraState, final Matrix4fc modelViewMatrix, final GpuBufferSlice terrainFog, final Vector4f fogColor, final boolean shouldRenderSky,
		CallbackInfo ci, @Local FrameGraphBuilder frame, @Local RenderTargetDescriptor screenSizeTargetDescriptor
	){
		((LevelTargetBundleI)this.targets).windowOpacity(frame.createInternal("wtr_window_opacity", screenSizeTargetDescriptor));
	}


	@Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;addAlwaysOnTopPass(Lcom/mojang/blaze3d/framegraph/FrameGraphBuilder;Lnet/minecraft/client/renderer/feature/FeatureRenderDispatcher$PreparedFrame;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;)V", shift = At.Shift.AFTER))
	private void render$applyOpacity(
		final GraphicsResourceAllocator resourceAllocator, final DeltaTracker deltaTracker, final boolean renderOutline, final CameraRenderState cameraState, final Matrix4fc modelViewMatrix, final GpuBufferSlice terrainFog, final Vector4f fogColor, final boolean shouldRenderSky,
		CallbackInfo ci, @Local FrameGraphBuilder frame, @Local(ordinal = 0) int screenWidth, @Local(ordinal = 1) int screenHeight
	){
		PostChain postChain = this.shaderManager.getPostChain(WindowToRealityClient.WINDOW_OPACITY_POST_CHAIN_ID, WindowToRealityClient.WINDOW_TARGETS);
		if (postChain != null) {
			postChain.addToFrame(frame, screenWidth, screenHeight, this.targets);
		}
	}

	
	@SuppressWarnings("null")
	@Inject(method = "addMainPass", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/framegraph/FramePass;executes(Ljava/lang/Runnable;)V"))
	private void addMainPass(
		final FrameGraphBuilder frame, final FeatureRenderDispatcher.PreparedFrame featureFrame, final GpuBufferSlice terrainFog, final LevelRenderState levelRenderState, final ProfilerFiller profiler, final ChunkSectionsToRender chunkSectionsToRender,
		CallbackInfo ci, @Local FramePass pass
	) {
		if (((LevelTargetBundleI)this.targets).windowOpacity() != null) {
			((LevelTargetBundleI)this.targets).windowOpacity(pass.readsAndWrites(((LevelTargetBundleI)this.targets).windowOpacity()));
		}
	}
}

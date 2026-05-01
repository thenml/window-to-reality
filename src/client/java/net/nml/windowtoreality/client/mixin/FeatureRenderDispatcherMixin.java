package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.feature.FeatureRendererMap;
import net.nml.windowtoreality.renderer.CutoutFeatureRenderer;

@Mixin(FeatureRenderDispatcher.class)
public abstract class FeatureRenderDispatcherMixin {
	@Shadow @Final private FeatureRendererMap featureRenderers;
	
	@Inject(method = "<init>", at = @At("TAIL"))
	private void add(CallbackInfo ci) {
		featureRenderers.put(CutoutFeatureRenderer.TYPE, new CutoutFeatureRenderer());
	}
}

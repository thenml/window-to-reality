package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher.PreparedFrame;
import net.nml.windowtoreality.client.util.PreparedFrameI;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@Inject(method = "lambda$addMainPass$0", at = @At("TAIL"))
	private void addMainPass(CallbackInfo ci, @Local PreparedFrame featureFrame) {
		((PreparedFrameI)featureFrame).executeCutout();
	}
}

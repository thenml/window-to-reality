package net.nml.windowtoreality.client.mixin;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.renderer.LevelRenderer;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
	/* 26.2 */
	@ModifyArg(require = 0, method = "lambda$render$0", at = @At(value = "INVOKE", target = "Lorg/joml/Vector4f;<init>(FFFF)V"), index = 3)
	private static float addOpacity$2(float original, @Local Vector4f fogColor) {
		return fogColor.w;
	}

	/* 26.1 */
	@ModifyArg(require = 0, method = "lambda$renderLevel$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;colorFromFloat(FFFF)I"), index = 0)
	private static float addOpacity$1(float original, @Local Vector4f fogColor) {
		return fogColor.w;
	}
}

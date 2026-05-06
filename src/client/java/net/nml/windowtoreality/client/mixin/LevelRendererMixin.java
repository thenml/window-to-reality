package net.nml.windowtoreality.client.mixin;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.renderer.LevelRenderer;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin {
	@ModifyArg(method = {"lambda$render$0" /* 26.2 */, "lambda$renderLevel$0" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;colorFromFloat(FFFF)I"), index = 0)
	private static float addOpacity(float original, @Local Vector4f fogColor) {
		return fogColor.w;
	}
}

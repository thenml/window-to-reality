package net.nml.windowtoreality.client.mixin;

import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.renderer.fog.FogRenderer;
import net.nml.windowtoreality.WindowToReality;

@Mixin(FogRenderer.class)
public abstract class FogRendererMixin {
	@Redirect(method = "computeFogColor(Lnet/minecraft/client/Camera;FLnet/minecraft/client/multiplayer/ClientLevel;IFLorg/joml/Vector4f;)V", at = @At(value = "INVOKE", target = "Lorg/joml/Vector4f;set(FFFF)Lorg/joml/Vector4f;"))
	private static Vector4f modifyFogColor(Vector4f dest, float r, float g, float b, float a) {
		if (WindowToReality.CLEAR_SKY) {
			return dest.set(0.0f);
		}
		return dest.set(r, g, b, a);
	}

	// possible future feat. but this needs a change in some codecs
	// @ModifyArg(method = "computeFogColor(Lnet/minecraft/client/Camera;FLnet/minecraft/client/multiplayer/ClientLevel;IFLorg/joml/Vector4f;)V", at = @At(value = "INVOKE", target = "Lorg/joml/Vector4f;set(FFFF)Lorg/joml/Vector4f;"), index = 3)
	// private static float modifyFogColor(float original, @Local(ordinal = 1) int color, @Local(ordinal = 3) float darkness, @Local FogType fogType, @Local(ordinal = 1) float darkenWorldAmount) {
	// 	float fogAlpha = ARGB.blueFloat(color);
	// 	if (darkness > 0.0F && fogType != FogType.LAVA && fogType != FogType.POWDER_SNOW) {
	// 		float brightness = Mth.square(1.0F - darkness);
	// 		fogAlpha *= brightness;
	// 	}

	// 	if (darkenWorldAmount > 0.0F) {
	// 		fogAlpha = Mth.lerp(darkenWorldAmount, fogAlpha, fogAlpha * 0.6F);
	// 	}

	// 	return fogAlpha;
	// }
}

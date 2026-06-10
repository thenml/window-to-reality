package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.Screenshot;
import net.minecraft.util.ARGB;

@Mixin(Screenshot.class)
public abstract class ScreenshotMixin {
    @Unique
    private static int alpha = 0;

	@ModifyConstant(method = "lambda$takeScreenshot$1", constant = @Constant(intValue = 0xff000000))
	private static int allowAlpha(int original) {
		return 0;
	}

    @Inject(method = "lambda$takeScreenshot$1", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/nio/ByteBuffer;getInt(I)I"))
    private static void grabAlpha(CallbackInfo ci, @Local(ordinal = 12) int argb) {
        alpha += ARGB.alpha(argb);
    }

    @ModifyArg(method = "lambda$takeScreenshot$1", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ARGB;color(IIII)I"), index = 0)
    private static int useAlpha(int original, @Local(ordinal = 0) int downscaleFactor) {
        int a = alpha / (downscaleFactor * downscaleFactor);
        alpha = 0;
        return a;
    }
}

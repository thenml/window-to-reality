package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.mojang.blaze3d.pipeline.RenderPipeline;

import net.minecraft.client.renderer.RenderPipelines;
import net.nml.windowtoreality.WindowToReality;

@Mixin(RenderPipelines.class)
public abstract class RenderPipelinesMixin {
	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;withFragmentShader(Ljava/lang/String;)Lcom/mojang/blaze3d/pipeline/RenderPipeline$Builder;"))
	private static RenderPipeline.Builder modifySkyShader(RenderPipeline.Builder builder, String fragmentShader) {
		if (WindowToReality.CLEAR_SKY && fragmentShader == "core/sky") {
			return builder.withFragmentShader(WindowToReality.of("clear_sky"));
		}
		return builder.withFragmentShader(fragmentShader);
	}
}

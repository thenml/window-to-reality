package net.nml.windowtoreality.client.mixin;

import java.util.stream.Stream;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack.Pose;

import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.feature.phase.SimpleFeatureRenderPhase;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.nml.windowtoreality.client.WindowToRealityClient;
import net.nml.windowtoreality.client.util.SubmitNodeCollectionI;
import net.nml.windowtoreality.renderer.CutoutFeatureRenderer;

@Mixin(SubmitNodeCollection.class)
public abstract class SubmitNodeCollectionMixin implements SubmitNodeCollectionI {
	private final SimpleFeatureRenderPhase cutout = new SimpleFeatureRenderPhase();

	@Override
	public SimpleFeatureRenderPhase cutout() {
		return cutout;
	}

	@ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/List;of([Ljava/lang/Object;)Ljava/util/List;"), index = 0)
	private Object[] addToList(Object[] args) {
		return Stream.concat(Stream.of(args), Stream.of(cutout)).toArray();
	}

	private void maybeSubmit(SimpleFeatureRenderPhase original, SubmitNode submit, Pose pose, RenderType renderType) {
		original.submit(submit);
		// if (renderType == WindowToRealityClient.cutoutRenderType) {
		// 	cutout.submit(new CutoutFeatureRenderer.Submit(pose, renderType));
		// } else {
		// }
	}

	@Redirect(method = "submitModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/feature/phase/SimpleFeatureRenderPhase;submit(Lnet/minecraft/client/renderer/feature/submit/SubmitNode;)V"))
	private <S> void submitModel(SimpleFeatureRenderPhase original, SubmitNode submit, @Local Pose pose, @Local RenderType renderType) {
		maybeSubmit(original, submit, pose, renderType);
	}

	@Redirect(method = "submitBlockModel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/feature/phase/SimpleFeatureRenderPhase;submit(Lnet/minecraft/client/renderer/feature/submit/SubmitNode;)V"))
	private <S> void submitBlockModel(SimpleFeatureRenderPhase original, SubmitNode submit, @Local Pose pose, @Local RenderType renderType) {
		maybeSubmit(original, submit, pose, renderType);
	}

	// @Redirect(method = "submitItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/feature/phase/SimpleFeatureRenderPhase;submit(Lnet/minecraft/client/renderer/feature/submit/SubmitNode;)V"))
	// private <S> void submitItem(SimpleFeatureRenderPhase original, SubmitNode submit, @Local RenderType renderType) {
	// 	maybeSubmit(original, submit, renderType);
	// }
}

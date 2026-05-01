package net.nml.windowtoreality.renderer;

import java.util.List;

import org.jspecify.annotations.NonNull;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.PoseStack.Pose;

import net.minecraft.client.renderer.feature.FeatureFrameContext;
import net.minecraft.client.renderer.feature.FeatureRendererType;
import net.minecraft.client.renderer.feature.RenderTypeFeatureRenderer;
import net.minecraft.client.renderer.feature.submit.SubmitNode;
import net.minecraft.client.renderer.rendertype.RenderType;

public class CutoutFeatureRenderer extends RenderTypeFeatureRenderer<CutoutFeatureRenderer.Submit> {
	public static final FeatureRendererType<CutoutFeatureRenderer.Submit> TYPE = FeatureRendererType.create("Window to Reality Cutout");

	@Override
	protected void buildGroup(final FeatureFrameContext context, final List<CutoutFeatureRenderer.Submit> submits) {
		for (CutoutFeatureRenderer.Submit submit : submits) {
			Pose pose = submit.pose();
			int color = 0xffFFFFFF;
			VertexConsumer builder = this.getVertexBuilder(submit.renderType());
			// cube
			cube(builder, pose, color);
		}
	}

	private static void cube(VertexConsumer b, PoseStack.Pose p, int color) {
		face(b, p, color, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, -1);
		face(b, p, color, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 1);
		face(b, p, color, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, -1, 0, 0);
		face(b, p, color, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0);
		face(b, p, color, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0);
		face(b, p, color, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, -1, 0);
	}

	private static void face(
		VertexConsumer b,
		PoseStack.Pose p,
		int color,

		float x1, float y1, float z1,
		float x2, float y2, float z2,
		float x3, float y3, float z3,
		float x4, float y4, float z4,

		float nx, float ny, float nz
	) {
		b.addVertex(p, x1, y1, z1).setColor(color).setNormal(p, nx, ny, nz).setLight(15).setOverlay(0).setUv(0, 0);
		b.addVertex(p, x2, y2, z2).setColor(color).setNormal(p, nx, ny, nz).setLight(15).setOverlay(0).setUv(0, 0);
		b.addVertex(p, x3, y3, z3).setColor(color).setNormal(p, nx, ny, nz).setLight(15).setOverlay(0).setUv(0, 0);
		b.addVertex(p, x4, y4, z4).setColor(color).setNormal(p, nx, ny, nz).setLight(15).setOverlay(0).setUv(0, 0);
	}

	public record Submit(Pose pose, RenderType renderType) implements SubmitNode {
		@Override
		public @NonNull FeatureRendererType<? extends SubmitNode> featureType() {
			return CutoutFeatureRenderer.TYPE;
		}
	}
}

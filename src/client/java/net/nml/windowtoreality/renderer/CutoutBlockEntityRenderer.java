package net.nml.windowtoreality.renderer;

import java.util.List;
import java.util.Map;

import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.FaceInfo;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.EndPortalRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.Util;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.nml.windowtoreality.block.CutoutBlockEntity;
import net.nml.windowtoreality.client.WindowToRealityClient;

@SuppressWarnings("null") // todo: remove
public class CutoutBlockEntityRenderer implements BlockEntityRenderer<CutoutBlockEntity, EndPortalRenderState> {
	private static final Vector3fc FROM = new Vector3f(0.0F, 0.0F, 0.0F);
	private static final Vector3fc TO = new Vector3f(1.0F, 1.0F, 1.0F);
	private static final Map<Direction, List<Vector3fc>> FACES = Util.makeEnumMap(
		Direction.class,
		direction -> {
			FaceInfo faceInfo = FaceInfo.fromFacing(direction);
			return List.of(
				faceInfo.getVertexInfo(0).select(FROM, TO),
				faceInfo.getVertexInfo(1).select(FROM, TO),
				faceInfo.getVertexInfo(2).select(FROM, TO),
				faceInfo.getVertexInfo(3).select(FROM, TO)
			);
		}
	);
	
	public CutoutBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public EndPortalRenderState createRenderState() {
		return new EndPortalRenderState();
	}

	@Override
	public void extractRenderState(@Nullable CutoutBlockEntity blockEntity, @Nullable EndPortalRenderState state, float tickProgress, Vec3 cameraPos, ModelFeatureRenderer.@Nullable CrumblingOverlay crumblingOverlay) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);
		state.facesToShow.clear();

		for (Direction direction : Direction.values()) {
			if (Block.shouldRenderFace(blockEntity.getBlockState(), blockEntity.getLevel().getBlockState(blockEntity.getBlockPos().relative(direction)), direction)) {
				state.facesToShow.add(direction);
			}
		}
	}

	@Override
	public void submit(@Nullable EndPortalRenderState state, PoseStack matrices, SubmitNodeCollector queue, CameraRenderState cameraState) {
		matrices.pushPose();
		queue.submitCustomGeometry(matrices, WindowToRealityClient.cutoutRenderType, (pose, buffer) -> {
			for (Direction direction : state.facesToShow) {
				for (Vector3fc faceVertex : FACES.get(direction)) {
					buffer.addVertex(pose, faceVertex);
				}
			}
		});
		matrices.popPose();
	}
}

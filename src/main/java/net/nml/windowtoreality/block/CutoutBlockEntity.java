package net.nml.windowtoreality.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nml.windowtoreality.ModRegistry;

public class CutoutBlockEntity extends BlockEntity {
	protected CutoutBlockEntity(final BlockEntityType<?> type, final BlockPos worldPosition, final BlockState blockState) {
		super(type, worldPosition, blockState);
	}

	public CutoutBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(ModRegistry.cutoutBlockEntity, worldPosition, blockState);
	}

	public boolean shouldRenderFace(Direction direction) {
		if (this.level == null) return false;
		BlockState other = this.level.getBlockState(this.getBlockPos().relative(direction));
		return Block.shouldRenderFace(this.getBlockState(), other, direction) && !other.is(ModRegistry.cutoutBlocksTag);
	}
}

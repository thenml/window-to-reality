package net.nml.windowtoreality.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.nml.windowtoreality.WindowToReality;

public class CutoutBlockEntity extends BlockEntity {
	protected CutoutBlockEntity(final BlockEntityType<?> type, final BlockPos worldPosition, final BlockState blockState) {
		super(type, worldPosition, blockState);
	}

	public CutoutBlockEntity(BlockPos worldPosition, BlockState blockState) {
		super(WindowToReality.cutoutBlockEntity, worldPosition, blockState);
	}
}

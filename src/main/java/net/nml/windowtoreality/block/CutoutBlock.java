package net.nml.windowtoreality.block;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CutoutBlock extends BaseEntityBlock {
	public CutoutBlock(Properties properties) {
		super(properties);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
		return new CutoutBlockEntity(worldPosition, blockState);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(CutoutBlock::new);
	}	

	@Override
	protected float getShadeBrightness(final BlockState state, final BlockGetter level, final BlockPos pos) {
		return 1.0F;
	}

	@Override
	protected RenderShape getRenderShape(final BlockState state) {
		return RenderShape.INVISIBLE;
	}
}

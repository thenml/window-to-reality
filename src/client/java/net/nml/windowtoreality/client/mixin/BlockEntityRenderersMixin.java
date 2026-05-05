package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.nml.windowtoreality.ModRegistry;
import net.nml.windowtoreality.client.renderer.CutoutBlockEntityRenderer;

@Mixin(BlockEntityRenderers.class)
public abstract class BlockEntityRenderersMixin {
	@Shadow private static <T extends BlockEntity, S extends BlockEntityRenderState> void register(final BlockEntityType<? extends T> type, final BlockEntityRendererProvider<T, S> renderer){}

	@Inject(method = "<clinit>", at = @At("RETURN"))
	private static void reg(CallbackInfo ci) {
		if (FabricLoader.getInstance().isModLoaded("fabric-api")) {
			register(ModRegistry.cutoutBlockEntity, CutoutBlockEntityRenderer::new);
		}
	}
}

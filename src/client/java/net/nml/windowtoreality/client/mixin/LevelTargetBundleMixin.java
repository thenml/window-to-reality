package net.nml.windowtoreality.client.mixin;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;

import net.minecraft.client.renderer.LevelTargetBundle;
import net.minecraft.resources.Identifier;
import net.nml.windowtoreality.client.WindowToRealityClient;
import net.nml.windowtoreality.client.util.LevelTargetBundleI;

@Mixin(LevelTargetBundle.class)
public abstract class LevelTargetBundleMixin implements LevelTargetBundleI {
	@Nullable @Unique
	public ResourceHandle<RenderTarget> windowOpacity;
	
	@Override
	@Nullable
	public ResourceHandle<RenderTarget> windowOpacity() {
		return this.windowOpacity;
	}

	@Override
	public void windowOpacity(@NonNull ResourceHandle<RenderTarget> handle) {
		this.windowOpacity = handle;
	}


	@Inject(method = "replace", at = @At("HEAD"), cancellable = true)
	private void replace(final Identifier id, final ResourceHandle<RenderTarget> handle, CallbackInfo ci) {
		if (id.equals(WindowToRealityClient.WINDOW_OPACITY_TARGET_ID)) {
			this.windowOpacity = handle;
			ci.cancel();
		}
	}


	@Inject(method = "get", at = @At("HEAD"), cancellable = true)
	private void get(final Identifier id, CallbackInfoReturnable<ResourceHandle<RenderTarget>> cir) {
		if (id.equals(WindowToRealityClient.WINDOW_OPACITY_TARGET_ID)) {
			cir.setReturnValue(this.windowOpacity);
		}
	}


	@Inject(method = "clear", at = @At("TAIL"))
	private void clear(CallbackInfo ci) {
		this.windowOpacity = null;
	}
}

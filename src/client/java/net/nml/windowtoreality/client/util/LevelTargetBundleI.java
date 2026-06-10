package net.nml.windowtoreality.client.util;

import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.resource.ResourceHandle;

public interface LevelTargetBundleI {
	@Nullable
	ResourceHandle<RenderTarget> windowOpacity();
	void windowOpacity(ResourceHandle<RenderTarget> importExternal);
}
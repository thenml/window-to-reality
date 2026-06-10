package net.nml.windowtoreality.client.util;

import org.jspecify.annotations.Nullable;
import com.mojang.blaze3d.pipeline.RenderTarget;

public interface LevelRendererI {
	@Nullable
	RenderTarget windowOpacityTarget();
}
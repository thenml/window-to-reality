package net.nml.windowtoreality.client.mixin;

import java.util.Objects;

import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.renderer.SubmitNodeCollection;
import net.minecraft.client.renderer.SubmitNodeStorage;
import net.minecraft.client.renderer.feature.FeatureFrameContext;
import net.minecraft.client.renderer.feature.FeatureRenderDispatcher;
import net.minecraft.client.renderer.feature.phase.FeatureRenderPhase;
import net.nml.windowtoreality.client.util.PreparedFrameI;
import net.nml.windowtoreality.client.util.SubmitNodeCollectionI;

@Mixin(FeatureRenderDispatcher.PreparedFrame.class)
public abstract class PreparedFrameMixin implements PreparedFrameI {
	@Shadow @Nullable private FeatureFrameContext context;
	@Shadow @Nullable private SubmitNodeStorage submitNodeStorage;
	@Shadow private void executePhase(final FeatureRenderPhase<?> phase, final FeatureFrameContext context){}

	@Override
	public void executeCutout() {
		FeatureFrameContext context = (FeatureFrameContext)Objects.requireNonNull(this.context);
		SubmitNodeStorage submitNodeStorage = (SubmitNodeStorage)Objects.requireNonNull(this.submitNodeStorage);

		for (SubmitNodeCollection collection : submitNodeStorage.getSubmitsPerOrder().values()) {
			this.executePhase(((SubmitNodeCollectionI)collection).cutout(), context);
		}
	}
}

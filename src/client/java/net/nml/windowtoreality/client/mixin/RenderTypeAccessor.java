package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.renderer.rendertype.RenderSetup;
import net.minecraft.client.renderer.rendertype.RenderType;

@Mixin(RenderType.class)
public interface RenderTypeAccessor {
	@Invoker("create")
	static RenderType create(String name, RenderSetup state) {
		throw new AssertionError();
	}
}

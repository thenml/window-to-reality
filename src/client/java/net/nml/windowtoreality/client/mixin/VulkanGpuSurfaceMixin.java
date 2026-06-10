package net.nml.windowtoreality.client.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.mojang.blaze3d.vulkan.VulkanGpuSurface;

@Mixin(VulkanGpuSurface.class)
public abstract class VulkanGpuSurfaceMixin {
    @ModifyArg(method = "configure", at = @At(value = "INVOKE", target = "Lorg/lwjgl/vulkan/VkSwapchainCreateInfoKHR;compositeAlpha(I)Lorg/lwjgl/vulkan/VkSwapchainCreateInfoKHR;"), index = 0)
    private int enableAlphaCompositing(int original) {
        // VK_COMPOSITE_ALPHA_PRE_MULTIPLIED_BIT_KHR = 0x2
        return 0x2;
    }
}

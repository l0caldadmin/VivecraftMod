package org.vivecraft.mixin.client_vr.blaze3d.vulkan;

import com.mojang.blaze3d.vulkan.VulkanCommandEncoder;
import com.mojang.blaze3d.vulkan.VulkanDevice;
import com.mojang.blaze3d.vulkan.VulkanQueue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.vivecraft.client.extensions.VulkanCommandEncoderExtension;
import org.lwjgl.vulkan.VkCommandBuffer;

@Mixin(VulkanCommandEncoder.class)
public abstract class VulkanCommandEncoderMixin implements VulkanCommandEncoderExtension {
    @Shadow private VulkanQueue.Submission submissionBuilder;
    @Shadow private VulkanDevice device;
    @Shadow protected abstract void endCommandBuffer();
    
    @Invoker("commandBuffer")
    public abstract VkCommandBuffer vivecraft$commandBuffer();
    
    @Unique
    @Override
    public void vivecraft$flush() {
        this.endCommandBuffer();
        if (this.submissionBuilder != null) {
            this.submissionBuilder.close();
        }
        this.submissionBuilder = this.device.graphicsQueue().beginSubmit();
    }
}

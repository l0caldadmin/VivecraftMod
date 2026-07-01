package org.vivecraft.client.extensions;

public interface VulkanCommandEncoderExtension {
    void vivecraft$flush();
    org.lwjgl.vulkan.VkCommandBuffer vivecraft$commandBuffer();
}

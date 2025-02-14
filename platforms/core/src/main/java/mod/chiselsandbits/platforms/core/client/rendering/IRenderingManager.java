package mod.chiselsandbits.platforms.core.client.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mod.chiselsandbits.platforms.core.client.IClientManager;
import mod.chiselsandbits.platforms.core.client.rendering.type.IRenderTypeManager;
import mod.chiselsandbits.platforms.core.fluid.FluidInformation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

/**
 * Gives access to the platforms specific rendering tasks.
 */
public interface IRenderingManager
{

    /**
     * Gives access to the clients rendering manager.
     *
     * @return The client rendering manager.
     */
    static IRenderingManager getInstance() {
        return IClientManager.getInstance().getRenderingManager();
    }

    /**
     * Renders a specific blockstate on the given position.
     *
     * @param last The current position matrix.
     * @param buffer The buffer to render into.
     * @param defaultBlockState The blockstate to render the model for.
     * @param model The model to render.
     * @param r The r color channel to apply.
     * @param g The g color channel to apply.
     * @param b The b color channel to apply-
     * @param combinedLight The combined light value to render.
     * @param combinedOverlay The combined overlay value to render.
     */
    void renderModel(
      PoseStack.Pose last,
      VertexConsumer buffer,
      BlockState defaultBlockState,
      BakedModel model,
      float r,
      float g,
      float b,
      int combinedLight,
      int combinedOverlay);

    /**
     * Gains access to the texture that is used to render a flowing fluid.
     *
     * @param fluidInformation The fluid to get the texture for.
     * @return The texture.
     */
    ResourceLocation getFlowingFluidTexture(final FluidInformation fluidInformation);

    /**
     * Gains access to the texture that is used to render a flowing fluid.
     *
     * @param fluid The fluid to get the texture for.
     * @return The texture.
     */
    ResourceLocation getFlowingFluidTexture(final Fluid fluid);

    /**
     * Gains access to the texture that is used to render a still fluid.
     *
     * @param fluidInformation The fluid to get the texture for.
     * @return The texture.
     */
    ResourceLocation getStillFluidTexture(final FluidInformation fluidInformation);

    /**
     * Gains access to the texture that is used to render a still fluid.
     *
     * @param fluid The fluid to get the texture for.
     * @return The texture.
     */
    ResourceLocation getStillFluidTexture(final Fluid fluid);

    /**
     * The render type manager.
     * Deals with the render types which are available on different platforms.
     *
     * @return The render type manager.
     */
    @NotNull
    IRenderTypeManager getRenderTypeManager();

    /**
     * Registers a new BEWLR (ISTER) for a specific item.
     *
     * @param item The item to register the BEWLR for.
     * @param renderer The BEWLR to register.
     */
    void registerISTER(final Item item, final BlockEntityWithoutLevelRenderer renderer);
}

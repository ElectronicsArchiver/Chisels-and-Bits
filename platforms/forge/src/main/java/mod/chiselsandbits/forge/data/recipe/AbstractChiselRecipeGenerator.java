package mod.chiselsandbits.forge.data.recipe;

import mod.chiselsandbits.api.item.chisel.IChiselItem;
import mod.chiselsandbits.api.util.ParamValidator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public abstract class AbstractChiselRecipeGenerator extends AbstractRecipeGenerator
{
    private final TagKey<Item> rodTag;
    private final TagKey<Item> ingredientTag;

    protected AbstractChiselRecipeGenerator(final DataGenerator generator, final Item result, TagKey<Item> ingredientTag)
    {
        super(generator, ParamValidator.isInstanceOf(result, IChiselItem.class));
        this.ingredientTag = ingredientTag;
        this.rodTag = Tags.Items.RODS_WOODEN;
    }

    protected AbstractChiselRecipeGenerator(
      final DataGenerator generator,
      final Item result,
      final TagKey<Item> rodTag,
      final TagKey<Item> ingredientTag)
    {
        super(generator, ParamValidator.isInstanceOf(result, IChiselItem.class));
        this.rodTag = rodTag;
        this.ingredientTag = ingredientTag;
    }

    @Override
    protected void buildCraftingRecipes(final Consumer<FinishedRecipe> writer)
    {
        ShapedRecipeBuilder.shaped(getItemProvider())
          .pattern("st")
          .pattern("  ")
          .define('s', rodTag)
          .define('t', ingredientTag)
          .unlockedBy("has_rod", has(rodTag))
          .unlockedBy("has_ingredient", has(ingredientTag))
          .save(writer);
    }
}
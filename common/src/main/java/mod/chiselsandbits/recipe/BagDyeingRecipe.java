package mod.chiselsandbits.recipe;

import mod.chiselsandbits.item.BitBagItem;
import mod.chiselsandbits.platforms.core.item.IDyeItemHelper;
import mod.chiselsandbits.registrars.ModRecipeSerializers;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BagDyeingRecipe extends CustomRecipe
{

    public BagDyeingRecipe(
      ResourceLocation name)
    {
        super(name);
    }

    @Override
    public boolean matches(final @NotNull CraftingContainer inv, final @NotNull Level worldIn)
    {
        return !getOutput(inv).getBag().isEmpty();
    }

    @Override
    public @NotNull ItemStack assemble(
      @NotNull CraftingContainer inv)
    {
        Result output = getOutput(inv);

        if (!output.getBag().isEmpty())
        {
            return BitBagItem.dyeBag(output.bag, output.color);
        }

        return ItemStack.EMPTY;
    }

    @NotNull
    private Result getOutput(
      CraftingContainer inv)
    {
        ItemStack bag = null;
        ItemStack dye = null;

        for (int x = 0; x < inv.getContainerSize(); ++x)
        {
            ItemStack is = inv.getItem(x);
            if (!is.isEmpty())
            {
                if (is.getItem() == Items.WATER_BUCKET || getDye(is) != null)
                {
                    if (dye == null)
                    {
                        dye = is;
                    }
                    else
                    {
                        return Result.EMPTY;
                    }
                }
                else if (is.getItem() instanceof BitBagItem)
                {
                    if (bag == null)
                    {
                        bag = is;
                    }
                    else
                    {
                        return Result.EMPTY;
                    }
                }
                else
                {
                    return Result.EMPTY;
                }
            }
        }

        if (bag != null && dye != null)
        {
            return new Result(bag, getDye(dye));
        }

        return Result.EMPTY;
    }

    private DyeColor getDye(
      ItemStack is)
    {
        return IDyeItemHelper.getInstance()
                 .getColorFromItem(is)
                 .orElseGet(() -> {
                     if (is.getItem() instanceof final DyeItem item)
                     {
                         return item.getDyeColor();
                     }

                     return null;
                 });
    }

    @Override
    public boolean canCraftInDimensions(final int width, final int height)
    {
        return width * height >= 2;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer()
    {
        return ModRecipeSerializers.BAG_DYEING.get();
    }

    private static class Result
    {
        private static final Result EMPTY = new Result(
          ItemStack.EMPTY,
          DyeColor.WHITE
        );

        private final ItemStack bag;
        private final DyeColor  color;

        public Result(
          ItemStack bag,
          DyeColor dye)
        {
            this.bag = bag;
            this.color = dye;
        }

        public ItemStack getBag()
        {
            return bag;
        }

        public DyeColor getColor()
        {
            return color;
        }
    }
}

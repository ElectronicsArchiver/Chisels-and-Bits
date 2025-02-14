package mod.chiselsandbits.registrars;

import mod.chiselsandbits.platforms.core.util.constants.Constants;
import mod.chiselsandbits.platforms.core.registries.deferred.IRegistrar;
import mod.chiselsandbits.platforms.core.registries.deferred.IRegistryObject;
import mod.chiselsandbits.recipe.BagDyeingRecipe;
import mod.chiselsandbits.recipe.modificationtable.ModificationTableRecipeSerializer;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ModRecipeSerializers
{
    private static final Logger                                                   LOGGER              = LogManager.getLogger();
    private static final IRegistrar<RecipeSerializer<?>>                          SERIALIZER_REGISTER = IRegistrar.create(Registry.RECIPE_SERIALIZER_REGISTRY, Constants.MOD_ID);
    public static final  IRegistryObject<SimpleRecipeSerializer<BagDyeingRecipe>> BAG_DYEING          =
      SERIALIZER_REGISTER.register("bag_dyeing", () -> new SimpleRecipeSerializer<>(BagDyeingRecipe::new));
    public static        IRegistryObject<ModificationTableRecipeSerializer>       MODIFICATION_TABLE  = SERIALIZER_REGISTER
      .register("modification_table", ModificationTableRecipeSerializer::new);

    private ModRecipeSerializers()
    {
        throw new IllegalStateException("Can not instantiate an instance of: ModRecipeSerializers. This is a utility class");
    }

    public static void onModConstruction()
    {
        LOGGER.info("Loaded recipe serializer configuration.");
    }

}

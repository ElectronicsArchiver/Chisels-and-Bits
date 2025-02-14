package mod.chiselsandbits.fabric.mixin.world.entity.item;

import mod.chiselsandbits.logic.BitStackPickupHandler;
import mod.chiselsandbits.logic.ScrollBasedModeChangeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityEventMixin
{
    private ItemEntity getThis() {
        return (ItemEntity) (Object) this;
    }

    @Inject(
      method = "playerTouch",
      cancellable = true,
      at = @At(
        value = "HEAD"
      )
    )
    private void onScroll(final Player player, final CallbackInfo ci)
    {
        if (BitStackPickupHandler.pickupItems(
          getThis(),
          player
        )) {
            ci.cancel();
        }
    }
}

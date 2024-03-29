package net.Slainlight.NoHunger.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.Slainlight.NoHunger.Main.config;

@Mixin(value = Item.class, priority = 500)
public class MixinItem
{
    @Inject(at = @At("HEAD"), method = "getMaxUseTime", cancellable = true)
    private void onGetMaxUseTime(ItemStack itemStack, CallbackInfoReturnable<Integer> info)
    {
        if (config.shouldInstantEat())
        {
            if (itemStack.getItem().isFood())
            {
                // Food items don't work with 0, so 1 is the next best thing.
                info.setReturnValue(1);
            }
        }
    }
}

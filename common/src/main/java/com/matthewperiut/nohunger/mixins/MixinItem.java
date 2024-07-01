package com.matthewperiut.nohunger.mixins;

import com.matthewperiut.nohunger.NoHungerConfigHandler;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Item.class, priority = 500)
public class MixinItem
{
    @Inject(at = @At("HEAD"), method = "getMaxUseTime", cancellable = true)
    private void onGetMaxUseTime(ItemStack stack, LivingEntity user, CallbackInfoReturnable<Integer> cir)
    {
        if (NoHungerConfigHandler.shouldInstantEat())
        {
            var f = stack.getItem().getComponents().get(DataComponentTypes.FOOD);
            if (f != null)
            {
                // Food items don't work with 0, so 1 is the next best thing.
                cir.setReturnValue(1);
            }
        }
    }
}

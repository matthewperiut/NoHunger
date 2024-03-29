package net.Slainlight.NoHunger.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.Slainlight.NoHunger.Main.config;

@Mixin(Item.Settings.class)
public class MixinItemSettings
{
    @Shadow
    FoodComponent foodComponent;

    @Inject(at = @At("HEAD"), method = "food")
    private void onSetFoodComponent(FoodComponent foodComponent, CallbackInfoReturnable<Item.Settings> info)
    {
        if (config.shouldMakeFoodUnstackable())
        {
            if (foodComponent != null)
            {
                ((Item.Settings) (Object) this).maxCount(1);
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "maxCount", argsOnly = true)
    private int adjustMaxCount(int maxCount)
    {

        if (config.shouldMakeFoodUnstackable())
        {
            if (foodComponent != null)
            {
                return 1;
            }
        }

        return maxCount;
    }
}

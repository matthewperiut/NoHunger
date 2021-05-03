package net.Slainlight.NoHunger.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.Slainlight.NoHunger.NoHungerConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItem
{
    @Inject(at = @At("HEAD"), method = "getMaxUseTime", cancellable = true)
    private void onGetMaxUseTime(ItemStack itemStack, CallbackInfoReturnable<Integer> info)
    {
        NoHungerConfig config = AutoConfig.getConfigHolder(NoHungerConfig.class).getConfig();

        if (config.shouldInstantEat()) {
            if (itemStack.getItem().isFood()) {
                // Food items don't work with 0, so 1 is the next best thing.
                info.setReturnValue(1);
            }
        }
    }
}

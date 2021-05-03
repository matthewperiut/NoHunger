package net.Slainlight.NoHunger.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.Slainlight.NoHunger.NoHungerConfig;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Settings.class)
public class MixinItemSettings
{
    @Shadow
    private FoodComponent foodComponent;

    @Inject(at = @At("HEAD"), method = "food")
    private void onSetFoodComponent(FoodComponent foodComponent, CallbackInfoReturnable<Item.Settings> info) {
        if(!NoHungerConfig.isRegistered())
        {
            AutoConfig.register(NoHungerConfig.class, JanksonConfigSerializer::new);
            NoHungerConfig.Register();
        }
        NoHungerConfig config = AutoConfig.getConfigHolder(NoHungerConfig.class).getConfig();
        if (config.shouldMakeFoodUnstackable()) {
            if (foodComponent != null) {
                ((Item.Settings) (Object) this).maxCount(1);
            }
        }
    }

    @ModifyVariable(at = @At("HEAD"), method = "maxCount")
    private int adjustMaxCount(int maxCount) {
        if(!NoHungerConfig.isRegistered())
        {
            AutoConfig.register(NoHungerConfig.class, JanksonConfigSerializer::new);
            NoHungerConfig.Register();
        }
        NoHungerConfig config = AutoConfig.getConfigHolder(NoHungerConfig.class).getConfig();
        if (config.shouldMakeFoodUnstackable()) {
            if (foodComponent != null) {
                return 1;
            }
        }

        return maxCount;
    }
}

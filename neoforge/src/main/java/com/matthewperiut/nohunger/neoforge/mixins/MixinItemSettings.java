package com.matthewperiut.nohunger.neoforge.mixins;

import com.matthewperiut.nohunger.NoHunger;
import com.matthewperiut.nohunger.NoHungerConfigHandler;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.neoforged.fml.loading.LoadingModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.Settings.class)
public abstract class MixinItemSettings
{
    @Inject(at = @At("RETURN"), method = "food", cancellable = true)
    private void adjustMaxCount(FoodComponent foodComponent, CallbackInfoReturnable<Item.Settings> cir) {
        if (!NoHungerConfigHandler.configAttempted) {
            NoHunger.isClothConfigPresent = LoadingModList.get().getModFileById("cloth_config") != null;
            NoHungerConfigHandler.init();
        }
        if (NoHungerConfigHandler.shouldMakeFoodUnstackable())
            cir.setReturnValue(cir.getReturnValue().component(DataComponentTypes.MAX_STACK_SIZE, 1));
    }
}
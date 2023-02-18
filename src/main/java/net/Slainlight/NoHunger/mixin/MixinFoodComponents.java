package net.Slainlight.NoHunger.mixin;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponents;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(FoodComponents.class)
public class MixinFoodComponents {
    @ModifyArgs(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/item/FoodComponents;RABBIT_STEW:Lnet/minecraft/item/FoodComponent;"),
                    to = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/item/FoodComponents;ROTTEN_FLESH:Lnet/minecraft/item/FoodComponent;")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/entity/effect/StatusEffect;II)V"),
            allow = 1
    )
    private static void NoHunger$rottenFleshCausesHunger(Args args) {
        args.set(0, StatusEffects.POISON);
        args.set(1, 150);
    }
}
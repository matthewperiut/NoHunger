package net.Slainlight.NoHunger.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

@Mixin(HuskEntity.class)
public class MixinHusk 
extends ZombieEntity {
    public MixinHusk(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyArg(method = "tryAttack",
               at = @At(value = "INVOKE", 
               target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"), index = 0)
    StatusEffectInstance NoHunger$husksGiveWeakness(StatusEffectInstance orig) {
        float f = this.world.getLocalDifficulty(this.getBlockPos()).getLocalDifficulty();
        return new StatusEffectInstance(StatusEffects.WEAKNESS, 90 * (int)f);
    }
}
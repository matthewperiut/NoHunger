package com.matthewperiut.nohunger;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class NoHungerManager extends HungerManager
{
    private final PlayerEntity player;

    public NoHungerManager(PlayerEntity player)
    {
        this.player = player;
    }

    public void add(int health) {
        player.heal(health);
    }

    @Override
    public void add(int hunger, float saturation) {
        add(hunger);
    }

    public void eat(FoodComponent foodComponent) {
        add(foodComponent.nutrition());
    }

    @Override
    public void update(PlayerEntity player) {

    }

    @Override
    public void readNbt(NbtCompound nbt)
    {

    }

    @Override
    public void writeNbt(NbtCompound nbt)
    {

    }

    @Override
    public int getFoodLevel()
    {
        return 10;
    }

    @Override
    public boolean isNotFull() { return player.getHealth() < player.getMaxHealth(); }

    @Override
    public void addExhaustion(float exhaustion) {
        // NO-OP
    }

    @Override
    public float getSaturationLevel() {
        return 0;
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        // NO-OP
    }

    @Override
    public void setSaturationLevel(float saturationLevel)
    {

    }
}

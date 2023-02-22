package net.Slainlight.NoHunger;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NoHungerManager extends HungerManager
{
    private final PlayerEntity player;

    public NoHungerManager(PlayerEntity player) {
        this.player = player;
    }

    public void NoHungerAdd(int health) {
        player.heal(health);
    }

    @Override
    public void add(int hunger, float saturation) {
        NoHungerAdd(hunger);
    }

    @Override
    public void eat(Item item, ItemStack itemStack) {
        if (item.isFood()) {
            FoodComponent foodComponent = item.getFoodComponent();
            NoHungerAdd(foodComponent.getHunger());
        }
    }

    @Override
    public boolean isNotFull() {
        return player.getHealth() < player.getMaxHealth();
    }

    @Override
    public int getFoodLevel() {
        return 20;
    }

    @Override
    public float getSaturationLevel() {
        return 0;
    }

    @Override
    public void update(PlayerEntity player) {
        // NO-OP
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        // NO-OP
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        // NO-OP
    }

    @Override
    public void addExhaustion(float exhaustion) {
        // NO-OP
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        // NO-OP
    }

    @Override
    public void setExhaustion(float exhaustion) {
        // NO-OP
    }

    @Override
    public void setSaturationLevel(float saturationLevel) {
        // NO-OP
    }
}

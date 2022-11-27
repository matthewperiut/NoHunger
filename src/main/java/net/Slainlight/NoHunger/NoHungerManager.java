package net.Slainlight.NoHunger;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class NoHungerManager extends HungerManager
{
    private static final int MAX_FOOD_LEVEL = 20;
    private static final float MAX_SATURATION_LEVEL = MAX_FOOD_LEVEL;

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

    @Override
    public void eat(Item item, ItemStack itemStack) {
        if (item.isFood()) {
            FoodComponent foodComponent = item.getFoodComponent();
            add(foodComponent.getHunger());
        }
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
        return 1;
    }

    @Override
    public boolean isNotFull() { return player.getHealth() < player.getMaxHealth(); }

    @Override
    public void addExhaustion(float exhaustion) {
        // NO-OP
    }

    @Override
    public float getSaturationLevel() {
        return -5;
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

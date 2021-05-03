package net.Slainlight.NoHunger;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class NoHungerManager extends HungerManager
{
    private static final int MAX_FOOD_LEVEL = 20;
    private static final float MAX_SATURATION_LEVEL = MAX_FOOD_LEVEL;

    private final PlayerEntity player;

    public NoHungerManager(PlayerEntity player) {
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
    public void update(PlayerEntity playerEntity) {
        // NO-OP
    }

    @Override
    public void fromTag(CompoundTag tag) {
        // NO-OP
    }

    @Override
    public void toTag(CompoundTag tag) {
        // NO-OP
    }

    @Override
    public int getFoodLevel() {
        return MAX_FOOD_LEVEL;
    }

    @Override
    public boolean isNotFull() { return player.getHealth() < player.getMaxHealth(); }

    @Override
    public void addExhaustion(float exhaustion) {
        // NO-OP
    }

    @Override
    public float getSaturationLevel() {
        return MAX_SATURATION_LEVEL;
    }

    @Override
    public void setFoodLevel(int foodLevel) {
        // NO-OP
    }

    @Override
    public void setSaturationLevelClient(float saturationLevel) {
        // NO-OP
    }
}

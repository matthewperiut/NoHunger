package com.matthewperiut.nohunger.neoforge.mixins;

import com.matthewperiut.nohunger.NoHungerConfigHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.matthewperiut.nohunger.NoHunger.raised;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {

    @Shadow @Final private static Identifier ARMOR_HALF_TEXTURE;

    @Shadow protected abstract int getHeartCount(@Nullable LivingEntity entity);

    @Inject(method = "renderFoodLevel", at = @At(value = "HEAD"), require = 0, cancellable = true)
    private void rejectRenderFoodLevel(DrawContext arg, CallbackInfo ci)
    {
        ci.cancel();
    }

    @Redirect(method = "renderAirLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"), require = 0)
    private void onDrawAirTexture(DrawContext context, Identifier identifier, int x, int y, int width, int height)
    {
        int v_offset = MinecraftClient.getInstance().player.isInSneakingPose() ? 0 : 7;
        if (NoHungerConfigHandler.shouldShowExp())
            v_offset = 0;
        if (NoHungerConfigHandler.shouldHideExp())
            v_offset = 7;

        if (y == 201 - raised)
        {
            if (MinecraftClient.getInstance().player.getArmor() > 0)
            {
                // Draw air bubbles (flipped)
                context.drawGuiTexture(identifier, (-1 * x) + context.getScaledWindowWidth() - 10, 191 - raised + v_offset, width, height);
                return;
            }
            context.drawGuiTexture(identifier, x, y + v_offset, width, height);
        }
    }
}

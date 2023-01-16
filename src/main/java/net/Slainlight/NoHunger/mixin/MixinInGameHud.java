package net.Slainlight.NoHunger.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.Slainlight.NoHunger.Main.config;
import static net.Slainlight.NoHunger.Main.raised;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud
{
    @Redirect(method = "renderStatusBars", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/hud/InGameHud;getHeartCount(Lnet/minecraft/entity/LivingEntity;)I"))
    private int onGetMountHealth(InGameHud hud, LivingEntity entity)
    {
        // This tricks the code into thinking that there will be a mount
        // health bar to be rendered instead of the hunger bar.
        return -1;
    }

    int flip(int min, int max, int given)
    {
        int sudoResult = given-min;
        int size = max-min;

        sudoResult = size - sudoResult;

        return sudoResult + min;
    }
    @Shadow
    protected abstract int getHeartCount(LivingEntity entity);
    @Shadow protected abstract LivingEntity getRiddenEntity();
    @Shadow protected abstract PlayerEntity getCameraPlayer();
    private static final int offset = 202;

    @Redirect(method = "renderStatusBars", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private void onDrawTexture(InGameHud instance, MatrixStack matrixStack, int x, int y, int u, int v, int e, int f)
    {
        int v_offset = getCameraPlayer().isInSneakingPose() ? 0 : 7;
        if (config.shouldShowExp())
            v_offset = 0;

        // Moves the armor bar down to where the hunger bar was.
        if (y == 191 - raised)
        {
            if (!(getHeartCount(getRiddenEntity()) > 0))
            {
                // Draw Armor (flipped)
                instance.drawTexture(matrixStack, flip(122, 194, x+101) + offset, 201 - raised + v_offset, u, v, e, f);
                return;
            }
        }

        if (y == 201 - raised)
        {
            if (getCameraPlayer().getArmor() > 0)
            {
                // Draw air bubbles (flipped)
                instance.drawTexture(matrixStack, flip(223, 295, x-101) - offset, 191 - raised + v_offset, u, v, e, f);
                return;
            }
        }

        instance.drawTexture(matrixStack, x, y + v_offset, u, v, e, f);
    }

    @Redirect(method = "drawHeart", at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"))
    private void onDrawHeartTexture(InGameHud instance, MatrixStack matrixStack, int x, int y, int u, int v, int e, int f)
    {
        if(getCameraPlayer().isInSneakingPose() || config.shouldShowExp())
            instance.drawTexture(matrixStack, x, y, u, v, e, f);
        else
            instance.drawTexture(matrixStack, x, y+7, u, v, e, f);
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    public void renderExperienceBar(MatrixStack matrices, int x, CallbackInfo ci)
    {
        if (!config.shouldShowExp())
            if(!getCameraPlayer().isInSneakingPose())
                ci.cancel();
    }

}
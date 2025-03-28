package dulkirmod.mixins;


import dulkirmod.DulkirMod;
import dulkirmod.config.DulkirConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityLivingBase.class})
public abstract class MixinEntityLivingBase extends MixinEntity {

    @Shadow
    public abstract boolean isPotionActive(Potion potionIn);

    @Shadow
    public abstract PotionEffect getActivePotionEffect(Potion potionIn);

    @Inject(method = "getArmSwingAnimationEnd()I", at = @At("HEAD"), cancellable = true)
    public void adjustSwingLength(CallbackInfoReturnable<Integer> cir) {
        if (!DulkirMod.Companion.getConfig().getGlobalEnabled()) return;
        DulkirConfig config = DulkirMod.Companion.getConfig();
        int length = 6;
        if (isPotionActive(Potion.digSpeed) && !config.getDisregardHaste()) {
            length -= (1 + getActivePotionEffect(Potion.digSpeed).getAmplifier());
            cir.setReturnValue(Math.max((int) (length * Math.exp(-config.getHasteSpeed())), 1));
        }
        else if (isPotionActive(Potion.digSlowdown) && !config.getDisregardFatigue()) {
            length += (1 + getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
            cir.setReturnValue(Math.max((int) (length * Math.exp(-config.getFatigueSpeed())), 1));
        }
        else {
            cir.setReturnValue(Math.max((int) (length * Math.exp(-config.getSpeed())), 1));
        }
    }
}

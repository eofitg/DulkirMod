package dulkirmod.mixins;


import dulkirmod.DulkirMod;
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
        int length;
        if (DulkirMod.Companion.getConfig().getDisregardHaste()) {
            length = 6;
        } else {
            if (this.isPotionActive(Potion.digSpeed)) {
                length = 6 - (1 + this.getActivePotionEffect(Potion.digSpeed).getAmplifier());
            } else if (this.isPotionActive(Potion.digSlowdown)) {
                length = 6 + (1 + this.getActivePotionEffect(Potion.digSlowdown).getAmplifier()) * 2;
            } else {
                length = 6;
            }
        }
        cir.setReturnValue(Math.max((int) (length * Math.exp(-DulkirMod.Companion.getConfig().getSpeed())), 1));
    }
}

package dulkirmod.mixins;

import dulkirmod.events.EntityRemovedEvent;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class MixinWorld {
    // Source: Aton addons https://github.com/FloppaCoding/AtonAddons/blob/main/src/main/java/atonaddons/mixins/MixinWorld.java
    @Inject(method = "removeEntity", at = @At("HEAD"))
    public void onEntityRemoved(Entity entityIn, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new EntityRemovedEvent(entityIn));
    }
}

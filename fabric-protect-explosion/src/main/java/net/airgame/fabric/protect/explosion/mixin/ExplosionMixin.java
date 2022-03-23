package net.airgame.fabric.protect.explosion.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Explosion.class)
public abstract class ExplosionMixin {

    @Shadow
    @Final
    @Nullable
    private Entity entity;

    @Shadow
    public abstract void clearAffectedBlocks();

    @Inject(at = @At(value = "INVOKE",
            target = "Ljava/util/List;addAll(Ljava/util/Collection;)Z",
            shift = At.Shift.AFTER),
            method = "collectBlocksAndDamageEntities")
    public void collectBlocksAndDamageEntitiesMixin(CallbackInfo info) {
        if (entity != null) {
            if (entity.getType() == EntityType.TNT) {
                return;
            }
            if (entity.getType() == EntityType.TNT_MINECART) {
                return;
            }
        }
        clearAffectedBlocks();
    }
}

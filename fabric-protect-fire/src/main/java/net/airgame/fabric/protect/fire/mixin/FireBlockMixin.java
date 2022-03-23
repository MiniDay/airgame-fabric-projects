package net.airgame.fabric.protect.fire.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.FireBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FireBlock.class)
public class FireBlockMixin {
//    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/FireBlock;getBurnChance(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)I"), method = "scheduledTick")
//    public int fireBurnBlockMixin(FireBlock instance, WorldView world, BlockPos pos) {
//        return 0;
//    }
//
//    @Inject(at = @At("HEAD"), method = "trySpreadingFire", cancellable = true)
//    public void trySpreadingFireMixin(World world, BlockPos pos, int spreadFactor, Random rand, int currentAge, CallbackInfo info) {
//        info.cancel();
//    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;hasHighHumidity(Lnet/minecraft/util/math/BlockPos;)Z", shift = At.Shift.BEFORE), method = "scheduledTick", cancellable = true)
    public void scheduledTickMixin(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo info) {
        info.cancel();
    }

}

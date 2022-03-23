package net.airgame.fabric.protect.fire.mixin;

import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.LavaFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(LavaFluid.class)
public class LavaFluidMixin {
    @Inject(at = @At("HEAD"), method = "onRandomTick", cancellable = true)
    public void onRandomTickMixin(World world, BlockPos pos, FluidState state, Random random, CallbackInfo info) {
        info.cancel();
    }
}

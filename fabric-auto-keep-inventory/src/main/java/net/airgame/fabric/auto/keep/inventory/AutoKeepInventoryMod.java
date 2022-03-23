package net.airgame.fabric.auto.keep.inventory;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoKeepInventoryMod implements DedicatedServerModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Auto-KeepInventory");

    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                world.getGameRules().get(GameRules.KEEP_INVENTORY).set(true, server);
                LOGGER.info("已为世界 {} 开启死亡不掉落。", world.getRegistryKey().getValue().toString());
            }
        });
    }
}

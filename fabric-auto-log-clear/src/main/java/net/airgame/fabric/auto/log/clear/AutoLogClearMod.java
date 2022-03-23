package net.airgame.fabric.auto.log.clear;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class AutoLogClearMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Auto-LogClear");

    @Override
    public void onInitialize() {
        removeOutDateFile(
                FabricLoader.getInstance().getGameDir().resolve("logs").toFile(),
                System.currentTimeMillis()
        );
    }

    private void removeOutDateFile(File file, long now) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    removeOutDateFile(subFile, now);
                }
            }
            return;
        }
        if (file.lastModified() + 7 * 24 * 60 * 1000 <= now) {
            if (file.delete()) {
                LOGGER.info("删除了过期日志文件: " + file.getAbsolutePath());
            }
        }
    }
}

package net.airgame.fabric.auto.restart;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoRestartMod implements DedicatedServerModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Auto-Restart");

    private ScheduledExecutorService scheduler;
    private MinecraftServer server;

    @Override
    public void onInitializeServer() {
        scheduler = Executors.newScheduledThreadPool(1);
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            AutoRestartMod.this.server = server;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 24);

            long delay = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
            scheduler.schedule(this::submitStopServer, delay, TimeUnit.MILLISECONDS);
            int second = (int) (delay / 1000);
            int min = second / 60;
            int hour = min / 60;
            LOGGER.info("已计划在 {} ms 后自动关闭服务器。({}h{}m{}s)", delay, hour, min % 60, second % 60);
        });
        ServerLifecycleEvents.SERVER_STOPPING.register(server -> scheduler.shutdownNow());
    }

    public void submitStopServer() {
        scheduler.scheduleWithFixedDelay(this::stopOnIdle, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * 在服务器空闲（没有玩家在线）时关闭
     */
    private void stopOnIdle() {
        if (!server.getPlayerManager().getPlayerList().isEmpty()) {
            return;
        }
        server.stop(false);
    }

}

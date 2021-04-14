package com.hrznstudio.galacticraft.misc.capes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hrznstudio.galacticraft.Galacticraft;
import net.minecraft.util.Util;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class CapesLoader {
    private Map players;
    private final Gson gson = new GsonBuilder().create();

    public CapesLoader() {
        load();
    }

    private void load() {
        Util.getMainWorkerExecutor().execute(() -> {
            long startLoad = System.currentTimeMillis();
            Galacticraft.logger.info("[Galacticraft] Loading capes data...");

            try {
                this.players = this.gson.fromJson(
                        IOUtils.toString(
                                new URL("https://raw.githubusercontent.com/StellarHorizons/Galacticraft-Rewoven/main/capes.json"),
                                Charset.defaultCharset()),
                        Map.class);
            } catch (IOException e) {
                Galacticraft.logger.warn("[Galacticraft] Failed to load capes.", e);
            }

            Galacticraft.logger.info("[Galacticraft] Loaded capes for {} players. (Took {}ms)", this.players.size(), System.currentTimeMillis()-startLoad);
        });
    }

    public Map getPlayers() {
        return players;
    }
}

/*
 * Copyright (c) 2019-2021 HRZN LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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

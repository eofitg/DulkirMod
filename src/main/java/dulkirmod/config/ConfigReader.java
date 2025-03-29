package dulkirmod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dulkirmod.DulkirMod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConfigReader {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final File configFile = new File(DulkirMod.getMc().mcDataDir, "config/swingspeed-config.json");

    private static boolean globalEnabled = true;
    private static float swingSpeed = 0f;
    private static float hasteSpeed = 0f;
    private static float fatigueSpeed = 0f;
    private static boolean ignoreHaste = true;
    private static boolean ignoreFatigue = true;

    public static void load() {
        if (!configFile.exists()) {
            save();
            return;
        }

        try {
            String configDataJson = new String(Files.readAllBytes(configFile.toPath()));
            ConfigData configData = gson.fromJson(configDataJson, ConfigData.class);
            globalEnabled = configData.swingSpeedEnabled;
            swingSpeed = configData.swingSpeed;
            hasteSpeed = configData.hasteSpeed;
            fatigueSpeed = configData.fatigueSpeed;
            ignoreHaste = configData.ignoreHaste;
            ignoreFatigue = configData.ignoreFatigue;
        } catch (Exception e) {
            System.out.println("[SwingSpeed] Failed to load config: " + e.getMessage());
        }
    }

    private static void save() {
        try {
            ConfigData configData = new ConfigData(globalEnabled, swingSpeed, hasteSpeed, fatigueSpeed, ignoreHaste, ignoreFatigue);
            String json = gson.toJson(configData);
            Files.write(configFile.toPath(), json.getBytes());
        } catch (IOException e) {
            System.out.println("[SwingSpeed] Failed to save config: " + e.getMessage());
        }
    }

    public static boolean toggleSwingSpeed() {
        globalEnabled = !globalEnabled;
        save();
        return globalEnabled;
    }

    public static void setSpeed(String type, float value) {
        switch (type) {
            case "speed":
                swingSpeed = value;
                break;
            case "hastespeed":
                hasteSpeed = value;
                break;
            case "fatiguespeed":
                fatigueSpeed = value;
                break;
        }
        save();
    }

    public static boolean toggleIgnore(String effect) {
        switch (effect) {
            case "haste":
                ignoreHaste = !ignoreHaste;
                break;
            case "fatigue":
                ignoreFatigue = !ignoreFatigue;
                break;
        }
        save();
        return "haste".equals(effect) ? ignoreHaste : "fatigue".equals(effect) && ignoreFatigue;
    }

    private static class ConfigData {
        private final boolean swingSpeedEnabled;
        private final float swingSpeed;
        private final float hasteSpeed;
        private final float fatigueSpeed;
        private final boolean ignoreHaste;
        private final boolean ignoreFatigue;

        public ConfigData(boolean swingSpeedEnabled, float swingSpeed, float hasteSpeed, float fatigueSpeed, boolean ignoreHaste, boolean ignoreFatigue) {
            this.swingSpeedEnabled = swingSpeedEnabled;
            this.swingSpeed = swingSpeed;
            this.hasteSpeed = hasteSpeed;
            this.fatigueSpeed = fatigueSpeed;
            this.ignoreHaste = ignoreHaste;
            this.ignoreFatigue = ignoreFatigue;
        }
    }

    public static boolean isGlobalEnabled() {
        return globalEnabled;
    }

    public static float getSwingSpeed() {
        return swingSpeed;
    }

    public static float getHasteSpeed() {
        return hasteSpeed;
    }

    public static float getFatigueSpeed() {
        return fatigueSpeed;
    }

    public static boolean isIgnoreHaste() {
        return ignoreHaste;
    }

    public static boolean isIgnoreFatigue() {
        return ignoreFatigue;
    }

}
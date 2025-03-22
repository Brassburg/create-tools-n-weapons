package net.brassburg.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.HashSet;

@Config(name = "brassburg")
public class ConfigOptions implements ConfigData {

    public boolean enabled = true;

//    @ConfigEntry.Gui.CollapsibleObject
//    public KeybindingConfig keybinding = new KeybindingConfig();

    public boolean invertActivation = false;
    public boolean sneakToExcavate = false;
    public boolean mineDiag = true;
    public int maxMinedBlocks = 40;
    public int maxMineDistance = 10;
    public boolean autoPickup = false;
    public boolean requiresTool = false;
    public boolean dontBreakTool = true;
    public boolean stopOnToolBreak = true;
    public boolean toolDurability = true;
    public boolean playerExhaustion = true;
    public float exhaustionMultiplier = 1.0f;
    public HashSet<String> tools = new HashSet<>();

//public DiggingKeyBinding keybind = new DiggingKeyBinding(true, true, "key.keyboard.grave.accent");
//    public static class KeybindingConfig {
//        public boolean enabled = true;
//        public boolean useKeybinding = true;
//        public String key = "key.keyboard.grave.accent";
//    }
}

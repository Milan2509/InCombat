package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = "server")
public class ServerConfig implements ConfigData {
    @Comment("The settings for while the player is in combat, and how the player becomes in combat")
    public CombatSettings combat_settings = new CombatSettings();
    @Comment("The settings for the grace effect, usable for pack or mod developers since there is no internal way to apply this effect")
    public GraceSettings grace_settings = new GraceSettings();

    public static class GraceSettings {
        @Comment("Restrictions while the player has grace")
        public CombatRestrictionsConfig restrictions = new CombatRestrictionsConfig();

//        @Comment("Should the player be invulnerable to all damage while having grace")
//        public boolean invulnerable = false;

        @Comment("Should the player be healed to full while having grace")
        public boolean heal = false;

        @Comment("Should the player not lose hunger while having grace")
        public boolean saturation = false;
    }

    public static class CombatSettings {
        @Comment("The duration that the player will be set in combat, in seconds, default = 15")
        public int duration = 15;

        @Comment("Damage sources that will set the player in combat")
        public InCombatSources sources = new InCombatSources();

        @Comment("Restrictions while the player is in combat")
        public CombatRestrictionsConfig restrictions = new CombatRestrictionsConfig();
    }

    public static class CombatRestrictionsConfig {
        @Comment("Disables block breaking while in combat, default = false")
        public boolean disable_block_breaking = false;
        @Comment("Blocks that can be broken while in combat, only applies when disable_block_breaking is enabled")
        public List<String> block_breaking_whitelist = new ArrayList<>();

        @Comment("Disables block placing while in combat, default = false")
        public boolean disable_block_placing = false;
        @Comment("Blocks that can be placed while in combat, only applies when disable_block_placing is enabled")
        public List<String> block_placing_whitelist = new ArrayList<>();

        @Comment("Should the player die when they disconnect while in combat (also applies to singleplayer), default = false")
        public boolean kill_on_disconnect = false;

        @Comment("Prevent health regeneration through food while in combat, healing requires golden apples or potions for example, default = false")
        public boolean prevent_natural_health_regeneration = false;
    }

    public static class InCombatSources {
        @Comment("Any type of damage the player takes, default = false")
        public boolean all = false;
        @Comment("If another player is the source of the damage, default = true")
        public boolean player = true;
        @Comment("Any entity damages the player, default = true")
        public boolean entity = true;
        @Comment("Damage taken when the player is on fire, default = false")
        public boolean fire = false;
        @Comment("When the player falls and takes damage, default = false")
        public boolean fall_damage = false;
    }

    public static class GraceRestrictionsConfig {
        @Comment("Disables block breaking while in combat, default = false")
        public boolean disable_block_breaking = false;
        @Comment("Blocks that can be broken while in combat, only applies when disable_block_breaking is enabled")
        public List<String> block_breaking_whitelist = new ArrayList<>();

        @Comment("Disables block placing while in combat, default = false")
        public boolean disable_block_placing = false;
        @Comment("Blocks that can be placed while in combat, only applies when disable_block_placing is enabled")
        public List<String> block_placing_whitelist = new ArrayList<>();
    }
}

package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = "server")
public class ServerConfig implements ConfigData {
    @Comment("The duration that the player will be set in combat, in seconds, default = 15")
    public int in_combat_duration = 15;

    @Comment("Restrictions while the player is in combat")
    public CombatRestrictions combat_restrictions = new CombatRestrictions();

    @Comment("Damage sources that will set the player in combat")
    public InCombatSources in_combat_sources = new InCombatSources();

    public static class CombatRestrictions{
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
}

package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = "server")
public class ServerConfig implements ConfigData {
    @Comment("The duration that the player will be set in combat, in seconds")
    public int in_combat_duration = 30;

    @Comment("Restrictions while the player is in combat")
    public CombatRestrictions combat_restrictions = new CombatRestrictions();

    @Comment("Damage sources that will set the player in combat")
    public InCombatSources in_combat_sources = new InCombatSources();

    public static class CombatRestrictions{
        public boolean disable_block_breaking = false;
        public List<String> block_breaking_whitelist = new ArrayList<>();

        public boolean disable_block_placing = false;
        public List<String> block_placing_whitelist = new ArrayList<>();

        public boolean kill_on_disconnect = false;

        public boolean prevent_natural_health_regeneration = false;
    }

    public static class InCombatSources {
        public boolean all = false;
        public boolean player = true;
        public boolean entity = true;
        public boolean fire = false;
        public boolean fall_damage = false;
    }
}

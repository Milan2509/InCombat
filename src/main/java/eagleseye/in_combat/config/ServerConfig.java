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

    public static class CombatRestrictions{
        public boolean disable_block_breaking = false;
        public List<String> block_breaking_whitelist = new ArrayList<>();
        public boolean disable_block_placing = false;
        public List<String> block_placing_whitelist = new ArrayList<>();
    }
}

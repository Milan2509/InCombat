package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "server")
public class ServerConfig implements ConfigData {
    @Comment("The duration that the player will be set in combat, in seconds")
    public int in_combat_duration = 30;
}

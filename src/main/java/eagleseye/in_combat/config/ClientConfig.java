package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "client")
public class ClientConfig implements ConfigData {
    public boolean render_hud = true;
    public int x_offset = 0;
    public int y_offset = 0;
}

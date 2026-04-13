package eagleseye.in_combat.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "client")
public class ClientConfig implements ConfigData {
    public ClientConfigSettings combat_hud_settings = new ClientConfigSettings();
    public ClientConfigSettings grace_hud_settings = new ClientConfigSettings();

    public static class ClientConfigSettings{
        public boolean render_hud = true;
        public int x_offset = 0;
        public int y_offset = 0;
    }

}

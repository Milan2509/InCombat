package eagleseye.in_combat.config;

import eagleseye.in_combat.InCombat;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = InCombat.MOD_ID)
public class ClientConfigWrapper extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("client")
    @ConfigEntry.Gui.Excluded
    public ClientConfig client = new ClientConfig();
}

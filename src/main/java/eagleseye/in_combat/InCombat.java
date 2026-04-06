package eagleseye.in_combat;

import eagleseye.in_combat.config.ServerConfig;
import eagleseye.in_combat.config.ServerConfigWrapper;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InCombat implements ModInitializer {
	public static final String MOD_ID = "in_combat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	ServerConfig serverConfig;

	@Override
	public void onInitialize() {
		// Config
		AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		serverConfig = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;
	}
}
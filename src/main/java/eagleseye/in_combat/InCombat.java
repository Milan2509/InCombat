package eagleseye.in_combat;

import eagleseye.in_combat.config.ClientConfig;
import eagleseye.in_combat.config.ClientConfigWrapper;
import eagleseye.in_combat.config.ServerConfig;
import eagleseye.in_combat.config.ServerConfigWrapper;
import eagleseye.in_combat.internals.client.HudRenderer;
import eagleseye.in_combat.internals.effect.CombatEffect;
import eagleseye.in_combat.internals.InCombatEventHandler;
import eagleseye.in_combat.internals.effect.GraceEffect;
import eagleseye.in_combat.internals.networking.NetworkHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InCombat implements ModInitializer {
	public static final String MOD_ID = "in_combat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static CombatEffect combatEffect;
	public static GraceEffect graceEffect;

	public static ServerConfig serverConfig;
	public static ClientConfig clientConfig;

	@Override
	public void onInitialize() {
		// Config
		AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		AutoConfig.register(ClientConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		serverConfig = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;
		clientConfig = AutoConfig.getConfigHolder(ClientConfigWrapper.class).getConfig().client;

		// Effects
		combatEffect = Registry.register(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "combat"), new CombatEffect());
		graceEffect = Registry.register(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "grace"), new GraceEffect());

		// Handlers
		InCombatEventHandler.register();
		HudRenderer.register();

		// Network
		NetworkHandler.registerPayloads();
		NetworkHandler.registerClient();
		NetworkHandler.registerSyncDataOnLogin();
	}
}
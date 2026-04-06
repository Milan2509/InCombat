package eagleseye.in_combat;

import eagleseye.in_combat.config.ServerConfig;
import eagleseye.in_combat.config.ServerConfigWrapper;
import eagleseye.in_combat.internals.InCombatEffect;
import eagleseye.in_combat.internals.InCombatManager;
import eagleseye.in_combat.internals.InCombatRestrictions;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InCombat implements ModInitializer {
	public static final String MOD_ID = "in_combat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static InCombatEffect inCombatEffect;

	public static ServerConfig serverConfig;

	@Override
	public void onInitialize() {
		// Config
		AutoConfig.register(ServerConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
		serverConfig = AutoConfig.getConfigHolder(ServerConfigWrapper.class).getConfig().server;

		// Registry
		inCombatEffect = Registry.register(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "in_combat"), new InCombatEffect());
		InCombatRestrictions.initializeEvents();
	}
}
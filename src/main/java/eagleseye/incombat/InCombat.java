package eagleseye.incombat;

import eagleseye.incombat.config.InCombatConfig;
import eagleseye.incombat.effect.InCombatEffect;
import eagleseye.incombat.integrations.WaystonesIntegrations;
import eagleseye.incombat.logic.InCombatHud;
import eagleseye.incombat.logic.PlayerDisconnectLogic;
import eagleseye.incombat.util.DependencyUtils;
import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class InCombat implements ModInitializer {
	public static final String MOD_ID = "incombat";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final InCombatConfig COMBAT_CONFIG = InCombatConfig.createAndLoad();

	public static final StatusEffect IN_COMBAT = new InCombatEffect();

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing: " + MOD_ID);

		//Register In Combat Effect
		Registry.register(Registries.STATUS_EFFECT, new Identifier(MOD_ID, "in_combat"), IN_COMBAT);

		//Register Logic
		InCombatHud.init();
		PlayerDisconnectLogic.init();

		//Mod Integrations
		if (DependencyUtils.isWaystonesLoaded()) WaystonesIntegrations.stopWaystoneInteraction();

		//Only on first load
		if(COMBAT_CONFIG.firstLoad()){
			List<String> effectsList = COMBAT_CONFIG.applyEffects();
			// Generic
			effectsList.add("bleed");
			effectsList.add("stunned");

			//Minecraft
			effectsList.add("minecraft.wither");
			effectsList.add("minecraft.poison");
			effectsList.add("minecraft.weakness");

			//Mod Specific
			effectsList.add("aether.inebriation");
			effectsList.add("alexscaves.bubbled");
			effectsList.add("born_in_chaos_v1.stun");
			effectsList.add("minecells.disarmed");


			COMBAT_CONFIG.firstLoad(false);
		}
	}
}
package eagleseye.incombat;

import eagleseye.incombat.config.InCombatConfig;
import eagleseye.incombat.effect.InCombatEffect;
import eagleseye.incombat.compat.WaystonesCompat;
import eagleseye.incombat.logic.BreakPlaceLogic;
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
		BreakPlaceLogic.init();

		//Mod Integrations
		if (DependencyUtils.isWaystonesLoaded()) WaystonesCompat.stopWaystoneInteraction();

		//Only on first load
		if(COMBAT_CONFIG.setDefaultLists()){
			List<String> effectsList = COMBAT_CONFIG.applyEffects();
			//Minecraft
			effectsList.add("minecraft:wither");
			effectsList.add("minecraft:poison");
			effectsList.add("minecraft:weakness");

			List<String> blockBreakingList = COMBAT_CONFIG.blockBreakingWhitelist();
			// Bosses of Mass Destruction
			blockBreakingList.add("bosses_of_mass_destruction:void_blossom");
			blockBreakingList.add("bosses_of_mass_destruction:obsidilith_rune");

			List<String> damageDealtList = COMBAT_CONFIG.damageDealtBlacklist();
			// Target Dummy
			damageDealtList.add("dummmmmmy:target_dummy");

			COMBAT_CONFIG.setDefaultLists(false);
		}
	}
}
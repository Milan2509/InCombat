package eagleseye.in_combat.internals.client;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ClientConfig;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.util.Identifier;

public class HudRenderer {
    private static final Identifier COMBAT_TEXTURE = Identifier.of(InCombat.MOD_ID, "textures/mob_effect/combat.png");
    private static final Identifier GRACE_TEXTURE = Identifier.of(InCombat.MOD_ID, "textures/mob_effect/grace.png");

    public static void register() {
        ClientConfig config = InCombat.clientConfig;

        HudRenderCallback.EVENT.register((context, tickDeltaManager) -> {
            if(!(ClientStates.isInCombat())) return;

            int screenWidth = context.getScaledWindowWidth();
            int screenHeight = context.getScaledWindowHeight();

            context.drawTexture(
                    COMBAT_TEXTURE,
                    screenWidth / 2 + config.combat_hud_settings.x_offset,
                    screenHeight + config.combat_hud_settings.y_offset,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );
        });

        HudRenderCallback.EVENT.register((context, tickDeltaManager) -> {
            if(!(ClientStates.isHasGrace())) return;

            int screenWidth = context.getScaledWindowWidth();
            int screenHeight = context.getScaledWindowHeight();

            context.drawTexture(
                    GRACE_TEXTURE,
                    screenWidth / 2 + config.combat_hud_settings.x_offset,
                    screenHeight + config.combat_hud_settings.y_offset,
                    0,
                    0,
                    16,
                    16,
                    16,
                    16
            );
        });
    }
}

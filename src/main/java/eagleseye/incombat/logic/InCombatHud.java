package eagleseye.incombat.logic;

import eagleseye.incombat.api.CombatCheck;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;

public class InCombatHud {

    public static void init(){
        Identifier texture = new Identifier("incombat", "textures/mob_effect/in_combat.png");

        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            PlayerEntity player = client.player;

            if(player == null) return;

            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            int textureWidth = 16;
            int textureHeight = 16;

            if(COMBAT_CONFIG.renderHud() && CombatCheck.isPlayerInCombat(player)){
                int x = (screenWidth - textureWidth) / 2;
                float y = (float) ((screenHeight - textureHeight) / 2) + ((float) screenHeight / 2.8f);

                context.drawTexture(texture, x + COMBAT_CONFIG.xOffset(), (int) y + COMBAT_CONFIG.yOffset(),
                        0, 0, 16, 16, textureWidth, textureHeight);
            }
        });
    }
}

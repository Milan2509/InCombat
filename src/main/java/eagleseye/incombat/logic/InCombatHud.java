package eagleseye.incombat.logic;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import eagleseye.incombat.api.CombatCheck;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;

public class InCombatHud {
    private static void init(){
        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.CHAT, EXAMPLE_LAYER, HudRenderingEntrypoint::render));
    }
    private static void render(){
//
//        HudRenderCallback.EVENT.register((context, tickDeltaManager) -> {
//            int color = 0xFFFF0000; // Red
//            int targetColor = 0xFF00FF00; // Green
//
//            // Total tick delta is stored in a field, so we can use it later.
//            totalTickDelta += tickDeltaManager.getTickDelta(true);
//
//            // "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
//            float lerpedAmount = MathHelper.abs(MathHelper.sin(totalTickDelta / 50F));
//            int lerpedColor = ColorHelper.Argb.lerp(lerpedAmount, color, targetColor);
//
//            // Draw a square with the lerped color.
//            // x1, x2, y1, y2, z, color
//            context.fill(0, 0, 100, 100, 0, lerpedColor);
//        });


//        Identifier texture = Identifier.of("incombat", "textures/mob_effect/in_combat.png");
//
//        HudRenderCallback.EVENT.register((context, tickDeltaManager) -> {
//            MinecraftClient client = MinecraftClient.getInstance();
//            PlayerEntity player = client.player;
//
//            if(player == null) return;
//
//            int screenWidth = client.getWindow().getScaledWidth();
//            int screenHeight = client.getWindow().getScaledHeight();
//
//            int textureWidth = 16;
//            int textureHeight = 16;
//
//            if(COMBAT_CONFIG.renderHud() && CombatCheck.isPlayerInCombat(player)){
//                int x = (screenWidth - textureWidth) / 2;
//                float y = (float) ((screenHeight - textureHeight) / 2) + ((float) screenHeight / 2.8f);
//
//                context.drawTexture(texture, x + COMBAT_CONFIG.xOffset(), (int) y + COMBAT_CONFIG.yOffset(),
//                        0, 0, 16, 16, textureWidth, textureHeight);
//            }
//        });
    }
}

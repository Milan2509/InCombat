package eagleseye.incombat.integrations;

import eagleseye.incombat.api.CombatCheck;
import net.blay09.mods.waystones.block.ModBlocks;
import net.blay09.mods.waystones.item.ModItems;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;

public class WaystonesIntegrations {
    public static void stopTeleportItems(PlayerEntity player){
        if (COMBAT_CONFIG.waystones() && CombatCheck.isPlayerInCombat(player)) {
            Item handItem = player.getStackInHand(player.getActiveHand()).getItem();

            if (handItem.equals(ModItems.warpStone) || handItem.equals(ModItems.boundScroll)
                    || handItem.equals(ModItems.returnScroll) || handItem.equals(ModItems.warpScroll)) {
                sendCancelMessage(player);
                player.stopUsingItem();
            }
        }
    }

    public static void stopWaystoneInteraction(){
        UseBlockCallback.EVENT.register((player, world, hand, blockHitResult) -> {
            if(CombatCheck.isPlayerInCombat(player) && checkForWaystones(world, blockHitResult)){
                sendCancelMessage(player);
                return ActionResult.FAIL;
            }
            return ActionResult.PASS;
        });
    }

    private static boolean checkForWaystones(World world, BlockHitResult blockHitResult){
        boolean waystone = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.waystone);
        boolean deepslate = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.deepslateWaystone);
        boolean blackstone = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.blackstoneWaystone);
        boolean sandy = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.sandyWaystone);
        boolean mossy = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.mossyWaystone);
        boolean end = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.endStoneWaystone);
        boolean portstone = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.portstone);
        boolean sharestone = world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(ModBlocks.sharestone);

        return waystone || deepslate || blackstone || sandy || mossy || end || portstone || sharestone || shareStones(world, blockHitResult);
    }

    private static boolean shareStones(World world, BlockHitResult blockHitResult){
        String[] colors = {"blue", "red", "yellow", "white", "black", "orange", "brown", "pink", "magenta",
                "purple", "green", "lime", "cyan", "gray", "light_gray", "light_blue"};

        for (String color : colors){
            if(world.getBlockState(blockHitResult.getBlockPos()).getBlock().equals(Registries.BLOCK.get(new Identifier("waystones:" + color + "_sharestone")))){
                return true;
            }
        }
        return false;
    }

    private static void sendCancelMessage(PlayerEntity player){
        player.sendMessage(Text.translatable("message.incombat.cancel_event").formatted(Formatting.RED), true);
    }
}

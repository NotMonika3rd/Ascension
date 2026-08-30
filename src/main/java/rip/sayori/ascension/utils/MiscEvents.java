package rip.sayori.ascension.utils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DimensionType;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import rip.sayori.ascension.Configs;
import rip.sayori.ascension.items.ModItems;

@Mod.EventBusSubscriber
public class MiscEvents {
    @SubscribeEvent
    public static void onRightClickOnBlock(PlayerInteractEvent.RightClickBlock event) {
        var world = event.getWorld();
        if (world.isRemote) return;
        if (world.getBlockState(event.getPos()).getBlock() == Blocks.BEDROCK) {
            var stack = event.getItemStack();
            var player = event.getEntityPlayer();
            if (stack.getItem().getHarvestLevel(stack, "pickaxe", player, Blocks.DIAMOND_BLOCK.getDefaultState()) >= 3) {
                if (stack.isItemStackDamageable() && !player.isCreative())
                    stack.damageItem(stack.getMaxDamage() / 8, player);
                world.spawnEntity(new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(ModItems.pieceOfBedrock)));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        var player = event.player;
        var world = event.player.world;
        if (world.isRemote) return;
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            if (player.posY < -10) {
                player.changeDimension(Configs.dim_id_limbo, (_, player1, _) -> player1.setPosition(player1.posX, 300, player1.posZ));
            }
        }
    }
}

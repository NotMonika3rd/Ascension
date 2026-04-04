package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.BaublesApi;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import rip.sayori.ascension.items.ModItems;
import rip.sayori.ascension.looting.LootTables;

import java.util.List;

@Mod.EventBusSubscriber
public class MinerAmulet extends BaubleItemBase{
    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.AMULET.getExpansion());
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent e){
        if(BaublesApi.isBaubleEquipped((EntityLivingBase) e.getPlayer(), ModItems.minerAmulet)){
            for(var i : LootTables.miner_amulet.get(e.getPlayer())){
                e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), i));
            }
        }
    }
}

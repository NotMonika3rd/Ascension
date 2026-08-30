package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.BaublesApi;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jspecify.annotations.Nullable;
import rip.sayori.ascension.items.ModItems;
import rip.sayori.ascension.looting.LootTables;

import java.util.List;

@Mod.EventBusSubscriber
public class MinerAmulet extends BaubleItemBase {
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent e) {
        if (BaublesApi.isBaubleEquipped((EntityLivingBase) e.getPlayer(), ModItems.minerAmulet)) {
            for (var i : LootTables.miner_amulet.get(e.getPlayer())) {
                e.getWorld().spawnEntity(new EntityItem(e.getWorld(), e.getPos().getX(), e.getPos().getY(), e.getPos().getZ(), i));
            }
        }
    }

    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.AMULET.getExpansion());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.miner_amulet").split("\\\\n")));
    }
}

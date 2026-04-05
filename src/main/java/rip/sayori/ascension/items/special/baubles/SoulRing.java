package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.BaublesApi;
import baubles.api.cap.BaublesContainer;
import baubles.api.module.IModule;
import baubles.common.module.ModuleAttribute;
import baubles.lib.util.AttrOpt;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jspecify.annotations.Nullable;
import rip.sayori.ascension.items.ModItems;

import java.util.List;
import java.util.UUID;

import static rip.sayori.ascension.items.ItemUtils.compoundSafe;

@Mod.EventBusSubscriber
public class SoulRing extends BaubleItemBase{
    public static final UUID ATTR_ID = UUID.fromString("2b59991f-5cff-488b-b22d-674a0ac30145");

    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.RING.getExpansion());
    }

    @Override
    public List<IModule> getModules(ItemStack itemstack, EntityLivingBase entity) {
        return List.of(new ModuleAttribute(ATTR_ID, () -> SharedMonsterAttributes.ATTACK_DAMAGE, 0.01f * compoundSafe(itemstack).getTagCompound().getLong("kills"), AttrOpt.MULTIPLY_BASE));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.soul_ring", compoundSafe(stack).getTagCompound().getLong("kills")).split("\\\\n")));
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event){
        Entity source = event.getSource().getTrueSource();
        if (source instanceof EntityLivingBase living && BaublesApi.getBaublesHandler(living) instanceof BaublesContainer container) {
            int index = BaublesApi.getIndexInBaubles(living, ModItems.soulRing, 0);
            if (index != -1) {
                var tagCompound = compoundSafe(container.getStackInSlot(index)).getTagCompound();
                tagCompound.setLong("kills", tagCompound.getLong("kills") + 1);
            }
        }
    }
}

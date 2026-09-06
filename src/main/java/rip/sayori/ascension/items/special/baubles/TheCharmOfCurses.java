package rip.sayori.ascension.items.special.baubles;

import baubles.api.BaubleType;
import baubles.api.BaubleTypeEx;
import baubles.api.attribute.AttributeManager;
import baubles.api.module.IModule;
import baubles.common.module.ModuleAttribute;
import baubles.lib.util.AttrOpt;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class TheCharmOfCurses extends BaubleItemBase {
    public static final UUID ATTR_ID = UUID.fromString("68d2b316-1664-45c5-859b-e6b8fa746898");

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase entity) {
        return (entity instanceof EntityPlayer) && (((EntityPlayer) entity).isCreative());
    }

    @Override
    public List<BaubleTypeEx> getTypes(ItemStack itemStack) {
        return List.of(BaubleType.CHARM.getExpansion());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.addAll(1, List.of(I18n.format("ascension.tooltip.the_charm_of_curses.0").split("\\\\n")));
    }

    @Override
    public List<IModule> getModules(ItemStack itemstack, EntityLivingBase entity) {
        return List.of(
                new ModuleAttribute(ATTR_ID, () -> AttributeManager.getAttribute(BaubleType.CHARM.getExpansion()), 1, AttrOpt.ADDITION)
        );
    }
}

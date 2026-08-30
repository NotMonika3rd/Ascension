package rip.sayori.ascension.items.special;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockBreaker extends Item {
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock() == Blocks.BEDROCK){
            worldIn.setBlockToAir(pos);
            var stack = player.getHeldItem(hand);
            stack.setCount(stack.getCount() - 1);
        }
        return EnumActionResult.SUCCESS;
    }
}

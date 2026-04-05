package rip.sayori.ascension.items;

import net.minecraft.item.Item;
import rip.sayori.ascension.items.special.BookOfBeyond;
import rip.sayori.ascension.items.special.baubles.BlacksmithsAmulet;
import rip.sayori.ascension.items.special.baubles.MinerAmulet;
import rip.sayori.ascension.items.special.baubles.SoulRing;

import static rip.sayori.ascension.items.ItemUtils.newItem;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item bookOfBeyond = newItem(new BookOfBeyond(), "book_of_beyond");
    public static final Item minerAmulet = newItem(new MinerAmulet(),"miner_amulet");
    public static final Item blacksmithsAmulet = newItem(new BlacksmithsAmulet(),"blacksmiths_amulet");
    public static final Item soulRing = newItem(new SoulRing(),"soul_ring");
}

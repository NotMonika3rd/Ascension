package rip.sayori.ascension.items;

import net.minecraft.item.Item;
import rip.sayori.ascension.items.special.BookOfBeyond;
import rip.sayori.ascension.items.special.baubles.MinerAmulet;

import static rip.sayori.ascension.items.ItemUtils.newItem;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item soulGem = newItem(new Item(), "soul_gem");
    public static final Item bookOfBeyond = newItem(new BookOfBeyond(), "book_of_beyond");
    public static final Item minerAmulet = newItem(new MinerAmulet(),"miner_amulet");
}

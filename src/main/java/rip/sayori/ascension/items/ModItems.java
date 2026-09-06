package rip.sayori.ascension.items;

import net.minecraft.item.Item;
import rip.sayori.ascension.items.special.BookOfBeyond;
import rip.sayori.ascension.items.special.baubles.*;

import static rip.sayori.ascension.items.ItemUtils.newItem;

@SuppressWarnings("unused")
public class ModItems {
    public static final Item bookOfBeyond = newItem(new BookOfBeyond(), "book_of_beyond");
    public static final Item minerAmulet = newItem(new MinerAmulet(), "miner_amulet");
    public static final Item blacksmithsAmulet = newItem(new BlacksmithsAmulet(), "blacksmiths_amulet");
    public static final Item soulRing = newItem(new SoulRing(), "soul_ring");
    public static final Item pieceOfBedrock = newItem(new Item(), "piece_of_bedrock");
    public static final Item totemOfSamsara = newItem(new TotemOfSamsara(), "totem_of_samsara");
    public static final Item theCharmOfCurses = newItem(new TheCharmOfCurses(), "the_charm_of_curses");
}

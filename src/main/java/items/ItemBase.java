package items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemBase extends Item {

    public ItemBase() {
        super(new FabricItemSettings().group(ItemGroup.MISC));
    }

    public ItemBase(ItemGroup itemGroup) {
        super (new FabricItemSettings().group(itemGroup));
    }
}

package io.gomint.server.inventory.item;

import io.gomint.inventory.item.ItemType;

import io.gomint.server.registry.RegisterInfo;
import io.gomint.taglib.NBTTagCompound;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 353 )
 public class ItemSugar extends ItemStack implements io.gomint.inventory.item.ItemSugar {

    // CHECKSTYLE:OFF
    public ItemSugar( short data, int amount ) {
        super( 353, data, amount );
    }

    public ItemSugar( short data, int amount, NBTTagCompound nbt ) {
        super( 353, data, amount, nbt );
    }
    // CHECKSTYLE:ON

    @Override
    public ItemType getType() {
        return ItemType.SUGAR;
    }

}
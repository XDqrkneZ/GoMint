package io.gomint.inventory.item;

import io.gomint.GoMint;

/**
 * @author geNAZt
 * @version 1.0
 */
public interface ItemFlowingLava extends ItemStack {

    /**
     * Create a new item stack with given class and amount
     *
     * @param amount which is used for the creation
     */
    static ItemFlowingLava create( int amount ) {
        return GoMint.instance().createItemStack( ItemFlowingLava.class, amount );
    }

}
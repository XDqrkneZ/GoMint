package io.gomint.server.inventory;

import io.gomint.server.network.PlayerConnection;
import io.gomint.server.network.packet.PacketInventoryContent;

/**
 * @author geNAZt
 * @version 1.0
 */
public class CreativeInventory extends Inventory {

    /**
     * Construct new creative inventory
     *
     * @param owner of this inventory, should be the server in this case
     * @param size  of the inventory
     */
    public CreativeInventory( InventoryHolder owner, int size ) {
        super( owner, size );
    }

    @Override
    public void sendContents( PlayerConnection playerConnection ) {
        PacketInventoryContent inventoryContent = new PacketInventoryContent();
        inventoryContent.setItems( getContents() );
        inventoryContent.setWindowId( WindowMagicNumbers.CREATIVE.getId() );
        playerConnection.addToSendQueue( inventoryContent );
    }

    @Override
    public void sendContents( int slot, PlayerConnection playerConnection ) {
        // This is a virtual inventory, only sendContents is used
    }

}

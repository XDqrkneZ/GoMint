/*
 * Copyright (c) 2017, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.server.entity.potion.effect;

import io.gomint.server.entity.Attribute;
import io.gomint.server.entity.AttributeModifier;
import io.gomint.server.entity.EntityLiving;
import io.gomint.server.entity.EntityPlayer;
import io.gomint.server.registry.RegisterInfo;

/**
 * @author geNAZt
 * @version 1.0
 */
@RegisterInfo( id = 5 )
public class Strength extends Effect {

    public Strength( int amplifier, long runoutTimer ) {
        super( amplifier, runoutTimer );
    }

    @Override
    public void apply( EntityLiving player ) {
        player.getAttributeInstance( Attribute.ATTACK_DAMAGE ).setModifier( AttributeModifier.STRENGTH_EFFECT, 1.3f * ( this.amplifier + 1 ) );
    }

    @Override
    public void update( long currentTimeMillis, float dT ) {

    }

    @Override
    public void remove( EntityLiving player ) {
        player.getAttributeInstance( Attribute.ATTACK_DAMAGE ).removeModifier( AttributeModifier.STRENGTH_EFFECT );
    }

}

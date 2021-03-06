/*
 * Copyright (c) 2017, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.server.player;

import io.gomint.entity.Entity;
import io.gomint.math.MathUtils;
import io.gomint.server.entity.EntityLiving;
import io.gomint.server.entity.EntityPlayer;
import io.gomint.server.entity.potion.effect.Effect;
import io.gomint.server.network.packet.PacketMobEffect;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectMap;
import it.unimi.dsi.fastutil.bytes.Byte2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.bytes.ByteOpenHashSet;
import it.unimi.dsi.fastutil.bytes.ByteSet;
import lombok.RequiredArgsConstructor;

/**
 * @author geNAZt
 * @version 1.0
 */
@RequiredArgsConstructor
public class EffectManager {

    private final EntityLiving living;
    private final Byte2ObjectMap<Effect> effects = new Byte2ObjectOpenHashMap<>();

    /**
     * Update effects (look if we can remove some)
     *
     * @param currentTimeMillis time when the tick started
     * @param dT                difference time for a full second
     */
    public void update( long currentTimeMillis, float dT ) {
        if ( !this.effects.isEmpty() ) {
            ByteSet removeEffects = null;

            for ( Byte2ObjectMap.Entry<Effect> entry : this.effects.byte2ObjectEntrySet() ) {
                if ( currentTimeMillis >= entry.getValue().getRunoutTimer() ) {
                    if ( removeEffects == null ) {
                        removeEffects = new ByteOpenHashSet();
                    }

                    removeEffects.add( entry.getByteKey() );
                } else {
                    entry.getValue().update( currentTimeMillis, dT );
                }
            }

            if ( removeEffects != null ) {
                for ( Byte removeEffect : removeEffects ) {
                    removeEffect( removeEffect );
                }
            }
        }
    }

    /**
     * Add or modify the effect
     *
     * @param id     of the effect
     * @param effect which should be added or modified
     */
    public void addEffect( byte id, Effect effect ) {
        // Check if we have a old effect
        Effect old = this.effects.get( id );
        if ( old != null ) {
            old.remove( this.living );

            sendPacket( PacketMobEffect.EVENT_MODIFY, id, effect.getAmplifier(),
                false, MathUtils.fastFloor( ( effect.getRunoutTimer() - this.living.getWorld().getServer().getCurrentTickTime() ) / 50f ) );
        } else {
            sendPacket( PacketMobEffect.EVENT_ADD, id, effect.getAmplifier(),
                effect.isVisible(), MathUtils.fastFloor( ( effect.getRunoutTimer() - this.living.getWorld().getServer().getCurrentTickTime() ) / 50f ) );
        }

        effect.apply( this.living );
        this.effects.put( id, effect );
    }

    /**
     * Remove effect
     *
     * @param id of the effect
     */
    public void removeEffect( byte id ) {
        // Remove effect
        Effect old = this.effects.remove( id );
        if ( old != null ) {
            old.remove( this.living );
            sendPacket( PacketMobEffect.EVENT_REMOVE, id, 0, false, 0 );
        }
    }

    /**
     * Remove all active effects
     */
    public void removeAll() {
        ByteSet removeEffects = null;

        for ( Byte2ObjectMap.Entry<Effect> entry : this.effects.byte2ObjectEntrySet() ) {
            if ( removeEffects == null ) {
                removeEffects = new ByteOpenHashSet();
            }

            removeEffects.add( entry.getByteKey() );
        }

        if ( removeEffects != null ) {
            for ( Byte removeEffect : removeEffects ) {
                removeEffect( removeEffect );
            }
        }
    }

    private void sendPacket( byte mode, byte id, int amplifier, boolean visible, int duration ) {
        PacketMobEffect mobEffect = new PacketMobEffect();
        mobEffect.setEntityId( this.living.getEntityId() );
        mobEffect.setAction( mode );
        mobEffect.setEffectId( id );
        mobEffect.setAmplifier( amplifier );
        mobEffect.setVisible( visible );
        mobEffect.setDuration( duration );

        if ( this.living instanceof EntityPlayer ) {
            ( (EntityPlayer) this.living ).getConnection().addToSendQueue( mobEffect );
        }

        if ( visible ) {
            for ( Entity entity : this.living.getAttachedEntities() ) {
                if ( entity instanceof EntityPlayer ) {
                    ( (EntityPlayer) entity ).getConnection().addToSendQueue( mobEffect );
                }
            }
        }
    }

    public boolean hasEffect( byte effectId ) {
        return this.effects.get( effectId ) != null;
    }

    public int getEffectAmplifier( byte effectId ) {
        Effect effect = this.effects.get( effectId );
        return ( effect == null ) ? -1 : effect.getAmplifier();
    }

    public void sendForPlayer( EntityPlayer player ) {
        for ( Byte2ObjectMap.Entry<Effect> entry : this.effects.byte2ObjectEntrySet() ) {
            if ( entry.getValue().isVisible() ) {
                PacketMobEffect mobEffect = new PacketMobEffect();
                mobEffect.setEntityId( this.living.getEntityId() );
                mobEffect.setAction( PacketMobEffect.EVENT_ADD );
                mobEffect.setEffectId( entry.getByteKey() );
                mobEffect.setAmplifier( entry.getValue().getAmplifier() );
                mobEffect.setVisible( true );
                mobEffect.setDuration( MathUtils.fastFloor( ( entry.getValue().getRunoutTimer() - this.living.getWorld().getServer().getCurrentTickTime() ) / 50f ) );
                player.getConnection().addToSendQueue( mobEffect );
            }
        }
    }

}

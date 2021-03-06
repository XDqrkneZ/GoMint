/*
 * Copyright (c) 2017, GoMint, BlackyPaw and geNAZt
 *
 * This code is licensed under the BSD license found in the
 * LICENSE file in the root directory of this source tree.
 */

package io.gomint.entity;

import io.gomint.command.CommandOutput;
import io.gomint.entity.passive.EntityHuman;
import io.gomint.gui.Form;
import io.gomint.gui.FormListener;
import io.gomint.inventory.Inventory;
import io.gomint.math.Vector;
import io.gomint.permission.PermissionManager;
import io.gomint.player.DeviceInfo;
import io.gomint.world.*;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author BlackyPaw
 * @author Digot
 * @version 1.0
 */
public interface EntityPlayer extends EntityHuman {

    /**
     * Set the new gamemode for this player
     *
     * @param gamemode The new gamemode to be used
     */
    void setGamemode( Gamemode gamemode );

    /**
     * Get the current gamemode
     *
     * @return The gamemode the player is in
     */
    Gamemode getGamemode();

    /**
     * Is this player op'ed?
     *
     * @return true when op'ed, false when not
     */
    boolean isOp();

    /**
     * Hide another player from this player
     *
     * @param player The player which should be hidden
     */
    void hidePlayer( EntityPlayer player );

    /**
     * Show a hidden player again
     *
     * @param player The player which should be shown again
     */
    void showPlayer( EntityPlayer player );

    /**
     * Check if given player is hidden to this player
     *
     * @param player The player which should be checked for
     * @return true if this player can't see the player given, false it it can
     */
    boolean isHidden( EntityPlayer player );

    /**
     * Opens a inventory for the player
     *
     * @param inventory which should be opened
     */
    void openInventory( Inventory inventory );

    /**
     * Close the given inventory
     *
     * @param inventory which should be closed
     */
    void closeInventory( Inventory inventory );

    /**
     * Send a message to the client, this uses the normal {@link ChatType} enum.
     *
     * @param message which should be send to the client
     */
    void sendMessage( String message );

    /**
     * Send a message with a given type to the client
     *
     * @param message which should be send
     * @param type    of the message
     */
    void sendMessage( ChatType type, String... message );

    /**
     * Check if player has a specific permission
     *
     * @param permission which should be checked for
     * @return true if the player has this permission, false if not
     */
    boolean hasPermission( String permission );

    /**
     * Get the view distance of this player
     *
     * @return radius of chunks this player can see
     */
    int getViewDistance();

    /**
     * Transfer player to another server
     *
     * @param host IP or Hostname for the user to connect to
     * @param port Of the new Server
     */
    void transfer( String host, int port );

    /**
     * Return the network latency
     *
     * @return network latency in ms
     */
    int getPing();

    /**
     * Display new form and get a listener for the response
     *
     * @param form which should be shown
     * @param <T>  type of return value from the response
     * @return form listener to attaching for response
     */
    <T> FormListener<T> showForm( Form form );

    /**
     * Set the server settings form
     *
     * @param form which will be set as the new settings form
     * @param <T>  type of return value from the response
     * @return form listener to attaching for response
     */
    <T> FormListener<T> setSettingsForm( Form form );

    /**
     * Remove the current stored settings form
     */
    void removeSettingsForm();

    /**
     * Get the players permission manager
     *
     * @return permission manager
     */
    PermissionManager getPermissionManager();

    /**
     * Is this player still online?
     *
     * @return true if online, false if not
     */
    boolean isOnline();

    /**
     * Locale of this player
     *
     * @return locale of the players client
     */
    Locale getLocale();

    /**
     * Disconnect a player for the given reason
     *
     * @param reason for disconnect
     */
    void disconnect( String reason );

    /**
     * Get absolute amount of xp in this entity
     *
     * @return absolute amount of xp
     */
    int getXP();

    /**
     * Percentage of xp for next level
     *
     * @return percentage of next level
     */
    float getXPPercentage();

    /**
     * Set the amount of xp this entity has
     *
     * @param xp of this entity
     */
    void setXP( int xp );

    /**
     * Get exp level of this entity
     *
     * @return exp level
     */
    int getLevel();

    /**
     * Set the level of the exp bar
     *
     * @param level of this entity
     */
    void setLevel( int level );

    /**
     * Play a sound for this player
     *
     * @param location of the sound in the client
     * @param sound    The sound which should be played
     * @param pitch    The pitch at which the sound should be played
     * @param data     additional data for the sound
     */
    void playSound( Vector location, Sound sound, byte pitch, SoundData data );

    /**
     * Play a sound for this player
     *
     * @param location of the sound in the client
     * @param sound    The sound which should be played
     * @param pitch    The pitch at which the sound should be played
     */
    void playSound( Vector location, Sound sound, byte pitch );

    /**
     * Send a particle to this player
     *
     * @param location of the particle in the client
     * @param particle which should be send
     */
    void sendParticle( Vector location, Particle particle );

    /**
     * Send a particle to this player
     *
     * @param location of the particle in the client
     * @param particle which should be send
     * @param data     which should be used to construct additional data needed to display the particle
     */
    void sendParticle( Vector location, Particle particle, ParticleData data );

    /**
     * Allow flying for the client
     *
     * @param value if true the client can fly, if false the client can't fly
     */
    void setAllowFlight( boolean value );

    /**
     * Get the setting for allowing flight
     *
     * @return true when the player can fly, false when not
     */
    boolean getAllowFlight();

    /**
     * Set flying state of the player
     *
     * @param value if true the player is flying, if false the player doesn't fly
     */
    void setFlying( boolean value );

    /**
     * Check if this player is flying
     *
     * @return true when flying otherwise false
     */
    boolean getFlying();

    /**
     * Send a title text to the user's screen, with an optional subtitle.
     *
     * @param title    Big text displayed in the middle of the screen
     * @param subtitle Smaller text displayed below the title text
     * @param fadein   duration for the fade in effect
     * @param duration which is used for how long the title should be shown
     * @param fadeout  duration for the fade out effect
     * @param unit     of duration multiplier
     */
    void sendTitle( String title, String subtitle, long fadein, long duration, long fadeout, TimeUnit unit );

    /**
     * Send a title without subtitle.
     *
     * @param title Big text displayed in the middle of the screen
     */
    void sendTitle( String title );

    /**
     * Send a title with title and subtitle.
     *
     * @param title    Big text displayed in the middle of the screen
     * @param subtitle Smaller text displayed below the title text
     *                 <p>
     *                 Default time for fadein and duration is 1 second
     */
    void sendTitle( String title, String subtitle );

    /**
     * Toggle gliding status of the player
     *
     * @param value true for gliding, false for not gliding
     */
    void setGliding( boolean value );

    /**
     * Is the player currently gliding?
     *
     * @return true when gliding, false when not
     */
    boolean isGliding();

    /**
     * Get information about the device the player is using
     *
     * @return device information from this player
     */
    DeviceInfo getDeviceInfo();

    /**
     * Disptach a command for this player
     *
     * @param command which should be dispatched
     * @return the output of this command
     */
    CommandOutput dispatchCommand( String command );

}

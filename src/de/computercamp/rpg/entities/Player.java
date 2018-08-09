package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.awt.Color;
import java.util.*;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends LivingBaseObject {
    private static final int INVENTORY_SIZE = 10;
    private List<Item> inventory = new ArrayList<>(INVENTORY_SIZE);
    private List<String> messagesForPlayer = new ArrayList<>();

    public Player(Vector2D position) {
        super(position);
        
    }

    @Override
    public char render() {
        if (health <= 0) {
            return 'X';
        }
        return '#';
    }

    /**
     * Just use this method to teleport the player
     *
     * @param newPosition the new position to teleport to
     */
    @Override
    public void setPosition(Vector2D newPosition) {
        Vector2D oldPosition = position;
        super.setPosition(newPosition);
        if (!map.onPlayerMove(this)) {
            position = oldPosition;
        }
    }

    /**
     * Moves this player upwards.
     */
    public void up() {
        if (health > 0) {
            position.y--;
            if (position.y < 0 || !map.onPlayerMove(this)) {
                position.y++;
            }
        }
    }

    /**
     * Moves the player downwards.
     */
    public void down() {
        if (health > 0) {
            position.y++;
            if (!map.onPlayerMove(this)) {
                position.y--;
            }
        }
    }

    /**
     * Moves this player to the right.
     */
    public void right() {
        if (health > 0) {
            position.x++;
            if (!map.onPlayerMove(this)) {
                position.x--;
            }
        }
    }

    /**
     * Moves this player to the left.
     */
    public void left() {
        if (health > 0) {
            position.x--;
            if (position.x < 0 || !map.onPlayerMove(this)) {
                position.x++;
            }
        }
    }

    public String renderHealth() {
        int hearts = health / 2;
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < hearts; i++) {
            string.append('\u2665');
        }
        for (int i = 0; i < MAX_HEALTH / 2 - hearts; i++) {
            string.append('\u2661');
        }
        return string.toString();
    }

    public void collectItem(Item item) {
        if (map == null) {
            throw new NullPointerException("Player removed from map");
        }
        if (inventory.size() < INVENTORY_SIZE) {
            map.removeObject(item);
            inventory.add(item);
        }
    }

    /**
     * @param item the item to removeObject
     * @return Returns true if the inventory contained the specified item.
     */
    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public String renderInventory() {
        StringBuilder string = new StringBuilder(Messages.inventory + ": \n");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            string.append(i + 1).append(". ").append(item.getSymbol()).append(" (").append(item.getDisplayName()).append(")\n");
        }
        return string.toString();
    }

    public void useItem(Item item) {
        if (item.use(this)) {
            inventory.remove(item);
        }
    }

    public void useItem(int inventoryIndex) {
        useItem(inventory.get(inventoryIndex));
    }

    public void sendMessage(String message) {
        messagesForPlayer.add(message);
    }

	public List<String> getMessagesForPlayer() {
		return messagesForPlayer;
	}
	public String renderMessagesForPlayer() {
		String toReturn = "";
		if (messagesForPlayer.size() > 0)
			toReturn += "\n>> " + messagesForPlayer.get(messagesForPlayer.size()-1);
		return toReturn;
	}
}

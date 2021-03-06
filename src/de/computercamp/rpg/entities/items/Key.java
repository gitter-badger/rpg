package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Key extends Item {

    public Key(Vector2D pos) {
        super(pos);
        symbol = '\ua720';
    }

    @Override
    public boolean use(Player player) {
        if (!player.isDead()) {
            player.setPosition(new Vector2D(1, 1));
            return true;
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.key;
    }
}

package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;

public class Monster extends NPC {

    public Monster(Player player, Vector2D position, MessageID message, long delay) {
        super(player, position, message, delay);
    }

    public void startFighting(Player player, Map map) {
        Thread rumlaufTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (health > 0) {
                        if (Math.abs(player.getPosition().x - position.x) > 1) {
                            if (player.getPosition().x < position.x) {
                                if (!(map.getObjectByPosition(new Vector2D(position.x - 1, position.y)) instanceof LivingBaseObject)) {

                                    setPosition(new Vector2D(position.x - 1, position.y));
                                }
                                else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x - 1, position.y))).getHealth() <= 0) {
                                    setPosition(new Vector2D(position.x - 1, position.y));
                                }
                            }
                            else {
                                if (!(map.getObjectByPosition(new Vector2D(position.x + 1, position.y)) instanceof LivingBaseObject)) {

                                    setPosition(new Vector2D(position.x + 1, position.y));
                                }
                                else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x + 1, position.y))).getHealth() <= 0) {
                                    setPosition(new Vector2D(position.x + 1, position.y));
                                }
                            }
                        }
                        else if (Math.abs(player.getPosition().y - position.y) > 0) {
                            if (player.getPosition().y < position.y) {
                                if (!(map.getObjectByPosition(new Vector2D(position.x, position.y - 1)) instanceof LivingBaseObject)) {

                                    setPosition(new Vector2D(position.x, position.y - 1));
                                }
                                else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y - 1))).getHealth() <= 0) {
                                    setPosition(new Vector2D(position.x, position.y - 1));
                                }
                            }
                            else {
                                if (!(map.getObjectByPosition(new Vector2D(position.x, position.y + 1)) instanceof LivingBaseObject)) {

                                    setPosition(new Vector2D(position.x, position.y + 1));
                                }
                                else if (((LivingBaseObject) map.getObjectByPosition(new Vector2D(position.x, position.y + 1))).getHealth() <= 0) {
                                    setPosition(new Vector2D(position.x, position.y + 1));
                                }
                            }
                        }
                        else {
                            player.decreaseHealth(5);
                        }

                        try {
                            Thread.sleep(500);
                        }
                        catch (InterruptedException e) {
                        }
                    }
                }

            }
        });

        rumlaufTimer.start();
    }

    @Override
    public void onHealthChanged() {
        if (health <= 0)
            monsterDespawn(this);
    }

    protected void monsterDespawn(Monster monster) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (despawn == 0) {
                    despawn = System.currentTimeMillis() + 5000;
                }
                while (!(System.currentTimeMillis() >= despawn && player.getMap().getMapContents().contains(monster))) {
                    try {
                        Thread.sleep(10);
                    }
                    catch (InterruptedException e) {
                    }
                }

                map.removeObject(monster);
            }
        });
        thread.start();
    }

    @Override
    public char render() {
        if (!isDead()) {
            return '\u2e0e';
        }
        else {


            return 'X';
        }
    }
}

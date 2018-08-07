package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle bundle;
    private static Locale locale;

    public static String npcWelcome;
    public static String npcMagician;
    public static String npcBadMagician;
    public static String npcWeaponsmith;
    public static String closeProgram;
    public static String healing_potion;
    public static String inventory;
    public static String key;
    public static String sword;
    public static String npcWaiting;

    private Messages() {
    }

    private static void reloadStrings() {
        bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", locale);
        npcWelcome = bundle.getString("npcWelcome");
        npcMagician = bundle.getString("npcMagician");
        npcBadMagician = bundle.getString("npcBadMagician");
        npcWeaponsmith = bundle.getString("npcWeaponsmith");
        closeProgram = bundle.getString("closeProgram");
        healing_potion = bundle.getString("healing_potion");
        inventory = bundle.getString("inventory");
        key = bundle.getString("key");
        sword = bundle.getString("sword");
        npcWaiting = bundle.getString("npcWaiting");
    }

    static {
        locale = Locale.getDefault();
        reloadStrings();
    }

    public static void changeLanguage(Locale locale) {
        Messages.locale = locale;
        reloadStrings();
    }
}

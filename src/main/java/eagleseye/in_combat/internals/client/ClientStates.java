package eagleseye.in_combat.internals.client;

public class ClientStates {
    private static boolean inCombat = false;
    private static boolean hasGrace = false;

    public static boolean isInCombat() {
        return inCombat;
    }

    public static void setInCombat(boolean value) {
        inCombat = value;
    }

    public static boolean isHasGrace() {
        return hasGrace;
    }

    public static void setHasGrace(boolean hasGrace) {
        ClientStates.hasGrace = hasGrace;
    }
}

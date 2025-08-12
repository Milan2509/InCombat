package eagleseye.incombat.util;

import net.fabricmc.loader.api.FabricLoader;

public class DependencyUtils {
    public static boolean isWaystonesLoaded() {
        return FabricLoader.getInstance().isModLoaded("waystones");
    }
}

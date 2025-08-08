package eagleseye.incombat.config;

import blue.endless.jankson.Comment;
import eagleseye.incombat.InCombat;
import io.wispforest.owo.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Modmenu(modId = InCombat.MOD_ID)
@Config(name = "incombat", wrapperName = "InCombatConfig")
public class InCombatConfigModel {
    @SectionHeader("general")
    @Comment("duration that the effect is applied, in ticks (20 = 1 second)")
    public int inCombatDuration = 300;

    @Comment("Disable natural health regen while in combat")
    public boolean disableNaturalRegen = false;

    @Comment("If the effect should always be active")
    public boolean alwaysActive = false;

    @Comment("The types of damage that will cause the 'in combat' effect")
    @Nest
    public ApplyEffectDamageSources damageSources = new ApplyEffectDamageSources();

    public static class ApplyEffectDamageSources{
        @Comment("if the effect applies always no matter the damage source")
        public boolean always = false;

        public boolean player = true;
        public boolean entity = true;
        public boolean fire = true;
        public boolean fallDamage = false;
        public boolean dragonBreath = true;
    }

    @Comment("If the mod should check for any effects that will cause the player to be in combat (see list below)")
    public boolean checkForEffects = true;
    @Comment("""
            Which effects should activate 'in combat'
            Checks if any effect CONTAINS the string (e.g. bleed, will allow for my_mod:bleeding or my_other_mod:bleed
            Checks for the translation key (e.g. regeneration would be effect.minecraft.regeneration)
            
            Recommended format for specific effects: <mod_id>:<effect>
            """)
    public List<String> applyEffects = new ArrayList<>();

    @SectionHeader("hudRenderer")
    @Comment("Should the HUD element be rendered")
    public boolean renderHud = true;
    @Comment("X offset coordinates")
    public int xOffset = 0;
    @Comment("Y offset coordinates")
    public int yOffset = 0;

    @ExcludeFromScreen
    @Comment("DO NOT TOUCH PLS")
    public boolean firstLoad = true;
}

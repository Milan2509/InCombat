# In Combat

![banner image](https://raw.githubusercontent.com/Milan2509/InCombat/1.20.1/.projectPage/icon.png)
[![discord_link_badge](https://img.shields.io/badge/Join_the_Discord-%235865F2?style=for-the-badge&logo=discord&logoColor=white&link=https%3A%2F%2Fdiscord.gg%2FX6TAsTz8NQ)](https://discord.gg/X6TAsTz8NQ)
[![Static Badge](https://img.shields.io/badge/NeoForge_Via_Sinytra_Connector-orange?style=for-the-badge&logo=modrinth&logoColor=white)](https://modrinth.com/mod/connector)

[![Static Badge](https://img.shields.io/badge/Report_Issues-red?style=for-the-badge&logo=github&logoColor=%23181717)](https://github.com/Milan2509/InCombat/issues)
[![Static Badge](https://img.shields.io/badge/Source_Code-%23181717?style=for-the-badge&logo=github&logoColor=white&link=https%3A%2F%2Fgithub.com%2FMilan2509%2FInCombat)](https://github.com/Milan2509/InCombat)

<details>
<summary>Show 1.20.1 Description</summary>

# Installation

**Requires**

- [oωo (owo-lib)](https://modrinth.com/mod/owo-lib)
- [Fabric API](https://modrinth.com/mod/fabric-api)

# Features ✨

### Becoming "In Combat"

There are a few ways the player can become in combat

- Receiving damage
- Dealing damage
- Having a specific status effect

### When "In Combat"

The player will have HUD element indicating that they are in combat.

When the player is in combat the following rules may apply.

- Death on disconnect
- Disable natural regeneration (disabled by default)
- Disable block breaking/placing (disabled by default)

### Configuration ⚙️


Basically everything is configurable inside the mod.

<details>
<summary>General</summary>
<ul>
    <li>"In Combat" duration (default = 300 ticks -> 15 seconds)</li>
    <li>Player should die on disconnect (default = true)</li>
    <li>Disable natural regeneration (default = false)</li>
    <li>Disable block breaking (default = false)</li>
    <li>Block breaking whitelist</li>
    <li>Disable block placing (default = false)</li>
    <li>Block placing whitelist</li>
    <li>Always "In Combat" (default = false)</li>
    <li>Can become "In Combat" while in creative (default = false)</li>
    <li>Which damage sources should set the player "In Combat"
        <ul>
            <li>All (default = false)</li>
            <li>Player (default = true)</li>
            <li>Any Entity (default = true)</li>
            <li>Fire (default = true)</li>
            <li>Fall Damage (default = false)</li>
            <li>Dragon Breath (default = true)</li>
        </ul>
    </li>
    <li>If effects should set the player "In Combat" (default = true)</li>
    <li>A list of all the effects that set the player "In Combat"</li>
    <li>Additional effects that the player gets when "In Combat"</li>
    <li>Attacking sets the player "In Combat"</li>
    <li>Attacked entities that do not set the player "In Combat"</li>
</ul>

</details>

<details>
<summary>HUD Renderer</summary>
<ul>
    <li>Should the HUD render (default = true)</li>
    <li>X Offset (default = 0)</li>
    <li>Y Offset (default = 0)</li>
</ul>
</details>

<details>
<summary>Mod Integrations</summary>
<ul>
    <li>A config option to enable/disable each mod integration</li>
</ul>
</details>

# Mod Integrations

**[Waystones](https://modrinth.com/project/LOpKHB2A)**

- Prevents teleporting while in combat

**[Spell Engine](https://modrinth.com/project/XvoWJaA2)**

- Spells are cost free to cast while out of combat

Suggest integrations in the [Discord](https://discord.gg/X6TAsTz8NQ)!

# Technical Info

Anyone can check if the player is in combat using the in combat API. Add this project to your mod with
the [Modrinth Maven](https://support.modrinth.com/en/articles/8801191-modrinth-maven). Once added checks can be done
like following

```
if(CombatCheck.isPlayerInCombat(PlayerEntity)){
  //Your code goes here...
}
```
</details>

# Installation ⚙️

**Requires**
- [Cloth Config API](https://modrinth.com/mod/cloth-config)
- [Fabric API](https://modrinth.com/mod/fabric-api)

# Features ✨
### The Effects
- There are 2 effects **Combat** and **Grace**
- Combat is the main effect applied when the player is in combat
- Grace is an effect for modpack and mod developers to be used for creating safe areas

### Becoming In Combat
- Receiving damage
- Dealing damage

### When In Combat
- A HUD element to indicate that you are in combat (see gallery)
- The following restrictions can be enabled/disabled in the config
    - Death on disconnect
    - Disable natural health regeneration
    - Disable block breaking/placing


# Configuration ⚙️
There are many config options allowing for full customization.

<details>
<summary>Server Config</summary>
<ul>
    <li><b>Combat Settings</b></li>
    <li>In Combat duration</li>
    <li>Should attacking entities put the player In Combat (with a blacklist)</li>
    <li>sdfsdf</li>
    <li>The damage sources that will put the player In Combat</li>
    <ul>
        <li>All damage sources</li>
        <li>Players</li>
        <li>Entities</li>
        <li>Fire damage</li>
        <li>Fall damage</li>
    </ul>
    <li>The restrictions while in combat</li>
    <ul>
        <li>Disable block breaking (with a whitelist)</li>
        <li>Disable block placing (with a whitelist)</li>
        <li>Kill the player on disconnect</li>
        <li>Prevent natural health regeneration</li>
    </ul>
    <li><b>Grace Settings</b></li>
    <li>The restrictions while having grace</li>
    <ul>
        <li>Disable block breaking (with a whitelist)</li>
        <li>Disable block placing (with a whitelist)</li>
        <li>Kill the player on disconnect</li>
        <li>Prevent natural health regeneration</li>
    </ul>
    <li>Heal while having grace</li>
    <li>Healing amount per tick while having grace</li>
</ul>
</details>

<details>
<summary>Client Config</summary>
<ul>
    <li><b>Configurable for both Combat and Grace</b></li>
    <li>Should the HUD be rendered</li>
    <li>HUD icon x-offset</li>
    <li>HUD icon y-offset</li>
</ul>
</details>


# In Combat API
There is an extensive API that allows mod developers to use many of the features of this mod.

- Add the mod to your project through the [Modrinth Maven API](https://support.modrinth.com/en/articles/8801191-modrinth-maven).
- Learn more about the [In Combat API](https://github.com/Milan2509/InCombat/blob/1.21.1/src/main/java/eagleseye/in_combat/api/InCombatAPI.java) here!
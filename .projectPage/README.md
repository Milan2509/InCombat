# In Combat

![banner image](https://raw.githubusercontent.com/Milan2509/InCombat/1.20.1/.projectPage/icon.png)
[![discord_link_badge](https://img.shields.io/badge/Join_the_Discord-%235865F2?style=for-the-badge&logo=discord&logoColor=white&link=https%3A%2F%2Fdiscord.gg%2FX6TAsTz8NQ)](https://discord.gg/X6TAsTz8NQ)
[![Static Badge](https://img.shields.io/badge/Forge_Via_Sinytra_Connector-orange?style=for-the-badge&logo=modrinth&logoColor=white)](https://modrinth.com/mod/connector)

[![Static Badge](https://img.shields.io/badge/Report_Issues-red?style=for-the-badge&logo=github&logoColor=%23181717)](https://github.com/Milan2509/InCombat/issues)
[![Static Badge](https://img.shields.io/badge/Source_Code-%23181717?style=for-the-badge&logo=github&logoColor=white&link=https%3A%2F%2Fgithub.com%2FMilan2509%2FInCombat)](https://github.com/Milan2509/InCombat)

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
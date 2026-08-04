# In Combat

![banner image](https://raw.githubusercontent.com/Milan2509/InCombat/1.20.1/.projectPage/icon.png)

[![discord_link_badge](https://img.shields.io/badge/Join_the_Discord-%235865F2?style=for-the-badge&logo=discord&logoColor=white&link=https%3A%2F%2Fdiscord.gg%2FX6TAsTz8NQ)](https://discord.gg/X6TAsTz8NQ)
[![Static Badge](https://img.shields.io/badge/NeoForge_Via_Sinytra_Connector-orange?style=for-the-badge&logo=modrinth&logoColor=white)](https://modrinth.com/mod/connector)

[![Static Badge](https://img.shields.io/badge/Report_Issues-red?style=for-the-badge&logo=github&logoColor=%23181717)](https://github.com/Milan2509/InCombat/issues)
[![Static Badge](https://img.shields.io/badge/Source_Code-%23181717?style=for-the-badge&logo=github&logoColor=white&link=https%3A%2F%2Fgithub.com%2FMilan2509%2FInCombat)](https://github.com/Milan2509/InCombat)

# Installation ⚙️

**Requires**

- [Cloth Config API](https://modrinth.com/mod/cloth-config)
- [Fabric API](https://modrinth.com/mod/fabric-api)

# Features ✨

## The Effects

- There are 2 effects: **Combat** and **Grace**
- **Combat** is the main effect applied when the player is in combat
- **Grace** is an effect for modpack and mod developers to be used for creating safe areas

## Becoming In Combat

There are 2 ways the player can become in combat:

- Receiving damage
- Dealing damage

## When In Combat

- A HUD element indicates that the player is in combat (see gallery)
- The following restrictions can be enabled/disabled in the config:
  - Death on disconnect
  - Disable natural health regeneration
  - Disable block breaking
  - Disable block placing

# Configuration ⚙️

There are many config options allowing for full customization.

## Server Config

### Combat Settings

- In Combat duration
- Should attacking entities put the player In Combat (with a blacklist)
- The damage sources that will put the player In Combat:
  - All damage sources
  - Players
  - Entities
  - Fire damage
  - Fall damage
- The restrictions while in combat:
  - Disable block breaking (with a whitelist)
  - Disable block placing (with a whitelist)
  - Kill the player on disconnect
  - Prevent natural health regeneration

### Grace Settings

- The restrictions while having Grace:
  - Disable block breaking (with a whitelist)
  - Disable block placing (with a whitelist)
  - Kill the player on disconnect
  - Prevent natural health regeneration
- Heal while having Grace
- Healing amount per tick while having Grace

## Client Config

### Configurable for Both Combat and Grace

- Should the HUD be rendered
- HUD icon X offset
- HUD icon Y offset

# In Combat API

There is an extensive API that allows mod developers to use many of the features of this mod.

- Add the mod to your project through the [Modrinth Maven API](https://support.modrinth.com/en/articles/8801191-modrinth-maven).
- Learn more about the [In Combat API](https://github.com/Milan2509/InCombat/blob/1.21.1/src/main/java/eagleseye/in_combat/api/InCombatAPI.java) here!

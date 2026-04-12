# Cobblemon Poketotem

This is a Server-Side only mod that adds PokeTotems to the game. PokeTotems are item versions of Pokemon.
There is two versions of PokeTotems, Normal and Clones.

Normal PokeTotems keep their UUID and OT data intact, while Clones get new UUIDs and OTs assigned.

Note, all versions of PokeTotems can be right-clicked to automatically convert into a Pokemon assuming 
the server-owner has not restricted the permissions for `/totemtopoke` and `/totemtopoke-redeem`

**Note to map-makers/server-owners:**

If you are planning on creating custom loot rewards using the Totems, it is recommended to use 
the Cloned PokeTotems instead of the Normal PokeTotems. 

## Requirements

- [Cobblemon v1.7.3+](https://modrinth.com/mod/cobblemon/)
- [GooeyLibs v3.1.1-1.21.x+](https://modrinth.com/mod/gooeylibs)

## Commands

### Requires No Permissions or Permission Node

- `/totemtopoke` - Converts the held PokeTotem into a Pokemon
    - Permission: `cobblemon_poketotem.command.totemtopoke`
- `/totemtopoke-redeem` - Converts the held PokeTotem Clone into a Pokemon
    - Permission: `cobblemon_poketotem.command.totemtopoke-redeem`

### Requires OP or Permission Node

- `/poketototem` - Opens the Normal PokeTotem conversion menu
  - Permission: `cobblemon_poketotem.command.poketototem`
- `/poketototem clone` - Opens the Clone PokeTotem conversion menu
  - Permission: `cobblemon_poketotem.command.poketototem`
- `/poketototem-server <player> <slot 0-5>` - Converts specific Pokemon Slot to a Normal PokeTotem and gives it to the player.  
  - Permission: `cobblemon_poketotem.command.poketototem-server`
- `/totemtopoke-server <player>` - Converts the currently held PokeTotem (if any) of a player into a Pokemon
    - Permission: `cobblemon_poketotem.command.totemtopoke-server`
- `/totemtopoke-redeem-server` - Converts the currently held PokeTotem Clone (if any) of a player into a Pokemon
    - Permission: `cobblemon_poketotem.command.totemtopoke-redeem`

## Cobblemon NPC Integrations

### Player Functions

#### `poketototem`

- `q.player.poketototem(<slot 0-5>)` - Converts the player's pokemon on a specific slot to a Normal PokeTotem

An Example of this can be found in the included Cobblemon NPC Dialogue [`cobblemon:cpt_poke_to_totem`](https://github.com/Station48XYZ/cobblemon-poketotem/blob/main/common/src/main/resources/data/cobblemon/dialogues/cpt_poke_to_totem.json)
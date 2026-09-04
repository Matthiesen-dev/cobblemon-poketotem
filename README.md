# Cobblemon Poketotem

<div>
  <img src="https://mods.matthiesen.dev/badges/matthiesenCore.svg" alt="Matthiesen Core">
  <img src="https://mods.matthiesen.dev/badges/cobblemon.svg" alt="Cobblemon">
  <img src="https://mods.matthiesen.dev/badges/gooeylibs.svg" alt="GooeyLibs">
</div>

This is a Server-Side only mod that adds PokeTotems to the game. PokeTotems are item versions of Pokemon.
There is two versions of PokeTotems, Normal and Clones.

Normal PokeTotems keep their UUID and OT data intact, while Clones get new UUIDs and OTs assigned.

Note, all versions of PokeTotems can be right-clicked to automatically convert into a Pokemon assuming 
the server-owner has not restricted the permissions for `/totemtopoke` and `/totemtopoke redeem`

**Note to map-makers/server-owners:**

If you are planning on creating custom loot rewards using the Totems, it is recommended to use 
the Cloned PokeTotems instead of the Normal PokeTotems. 

## Requirements

- [Matthiesen Core](https://modrinth.com/mod/matthiesen-core/)
- [Cobblemon v1.7.3+](https://modrinth.com/mod/cobblemon/)
- [GooeyLibs v3.1.1-1.21.x+](https://modrinth.com/mod/gooeylibs)
- [Fabric API](https://modrinth.com/mod/fabric-api) (Fabric only)
- [Forge Config API Port](https://modrinth.com/mod/forge-config-api-port) (Fabric only)

## Docs

Documentation for this mod can be found at [mods.matthiesen.dev](https://mods.matthiesen.dev/cobblemon-poketotem/)

## Version Compatibility

| Minecraft Version | Matthiesen Core Version | Mod Version |
|-------------------|-------------------------|-------------|
| 1.21.1            | 1.x.x                   | 1.x.x       |

## FastStats Metrics

This mod uses [FastStats](https://faststats.dev) to collect anonymous usage statistics. This helps the developer understand
how this mod is being used and improve it over time. You can learn more about the data collected and how it is used by visiting
[FastStats: Information](https://faststats.dev/info).

You can also view the data collected by this mod on the [FastStats: Cobblemon Poketotem](https://faststats.dev/project/cobblemon-poketotem) page.

To opt out of this data collection, set the `enabled` property to `false` in the `<game_directory>/config/matthiesen_core/metrics.properties` file.

## License

MIT - see `LICENSE`.
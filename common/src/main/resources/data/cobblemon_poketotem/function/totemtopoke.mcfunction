# Cleanup Advancement for future use
advancement revoke @s only cobblemon_poketotem:totem_click

# If Normal PokeTotem
execute if items entity @s weapon.* *[minecraft:custom_data~{CPT_FN:"cpt-poke-totem-fn"}] run return run totemtopoke

# If Clone PokeTotem
execute if items entity @s weapon.* *[minecraft:custom_data~{CPT_FN:"cpt-poke-totem-clone-fn"}] run return run totemtopoke-redeem

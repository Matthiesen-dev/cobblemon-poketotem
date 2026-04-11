package xyz.station48.common.cobblemon_poketotem.permissions;

import com.cobblemon.mod.common.api.permission.Permission;
import com.cobblemon.mod.common.api.permission.PermissionLevel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import xyz.station48.common.cobblemon_poketotem.Constants;

public class CobblemonPokeTotemPermission implements Permission {
    private final String node;
    private final PermissionLevel level;
    private final ResourceLocation identifier;
    private final String literal;

    public CobblemonPokeTotemPermission(String node, PermissionLevel level) {
        this.node = node;
        this.level = level;
        this.identifier = ResourceLocation.fromNamespaceAndPath(Constants.ModId, this.node);
        this.literal = Constants.ModId + "." + this.node;
    }

    @Override
    public @NotNull ResourceLocation getIdentifier() {
        return identifier;
    }

    @Override
    public @NotNull String getLiteral() {
        return literal;
    }

    @Override
    public @NotNull PermissionLevel getLevel() {
        return level;
    }

    // Optional: equals, hashCode, and toString methods for data class behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CobblemonPokeTotemPermission that = (CobblemonPokeTotemPermission) o;
        return java.util.Objects.equals(node, that.node) &&
                java.util.Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(node, level);
    }

    @Override
    public String toString() {
        return "CobblemonPokeTotem{" +
                "node='" + node + '\'' +
                ", level=" + level +
                ", identifier=" + identifier +
                ", literal='" + literal + '\'' +
                '}';
    }
}
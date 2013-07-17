package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityLeash;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LeadKnot;

public class CraftLeadKnot extends CraftHanging implements LeadKnot {
    public CraftLeadKnot(CraftServer server, EntityLeash entity) {
        super(server, entity);
    }

    @Override
    public EntityLeash getHandle() {
        return (EntityLeash) entity;
    }

    public EntityType getType() {
        return EntityType.LEAD_KNOT;
    }
}

package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

// CraftBukkit start
import org.bukkit.entity.Player;
import org.bukkit.event.hanging.HangingPlaceEvent;
// CraftBukkit end

public class ItemLeash extends Item {

    public ItemLeash(int i) {
        super(i);
        this.a(CreativeModeTab.i);
    }

    public boolean interactWith(ItemStack itemstack, EntityHuman entityhuman, World world, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = world.getTypeId(i, j, k);

        if (Block.byId[i1] != null && Block.byId[i1].d() == 11) {
            if (world.isStatic) {
                return true;
            } else {
                a(entityhuman, world, i, j, k);
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean a(EntityHuman entityhuman, World world, int i, int j, int k) {
        EntityLeash entityleash = EntityLeash.b(world, i, j, k);
        boolean flag = false;
        double d0 = 7.0D;
        List list = world.a(EntityInsentient.class, AxisAlignedBB.a().a((double) i - d0, (double) j - d0, (double) k - d0, (double) i + d0, (double) j + d0, (double) k + d0));

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityInsentient entityinsentient = (EntityInsentient) iterator.next();

                if (entityinsentient.bH() && entityinsentient.bI() == entityhuman) {
                    if (entityleash == null) {
                        entityleash = EntityLeash.a(world, i, j, k);
                        // CraftBukkit start
                        Player who = (entityhuman == null) ? null : (Player) entityhuman.getBukkitEntity();
                        org.bukkit.block.Block blockClicked = world.getWorld().getBlockAt(i, j, k);

                        HangingPlaceEvent event = new HangingPlaceEvent((org.bukkit.entity.Hanging) entityleash.getBukkitEntity(), who, blockClicked, org.bukkit.block.BlockFace.SELF);
                        world.getServer().getPluginManager().callEvent(event);

                        if (event.isCancelled()) {
                            entityleash.die();
                            return flag;
                        }
                        // CraftBukkit end
                    }

                    entityinsentient.b(entityleash, true);
                    flag = true;
                }
            }
        }

        return flag;
    }
}

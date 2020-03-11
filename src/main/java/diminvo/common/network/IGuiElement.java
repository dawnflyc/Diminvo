package diminvo.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 05:17
 **/
public interface IGuiElement {

    Object getServerGuiElement(EntityPlayer player, World world, int x, int y, int z);

    Object getClientGuiElement(EntityPlayer player, World world, int x, int y, int z);

}

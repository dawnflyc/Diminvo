package diminvo.common.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 05:15
 **/
public class GuiHandler implements IGuiHandler {
   private static final Map<Integer,IGuiElement> ELEMENTS =new HashMap<>();

   private static final Map<String,Integer> E_ID=new HashMap<>();

    public static void registry(String id,IGuiElement IGuiElement){
        ELEMENTS.put(ELEMENTS.size(), IGuiElement);
        E_ID.put(id,E_ID.size());
    }

    public static int getGuiId(String id){
        return E_ID.get(id);
    }

    public static IGuiElement getGuiElement(String id){
        return ELEMENTS.get(E_ID.get(id));
    }
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return ELEMENTS.get(ID).getServerGuiElement(player,world,x,y,z);
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return ELEMENTS.get(ID).getClientGuiElement(player,world,x,y,z);
    }
}

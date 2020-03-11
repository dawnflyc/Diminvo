package diminvo.common.item;

import diminvo.Diminvo;
import diminvo.common.event.EventHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @program: DimensionInventory
 * @description:
 * @author: xiaofei
 * @create: 2020-03-04 21:53
 **/
public class LockItem extends Item {

    public static final LockItem LOCK_ITEM =new LockItem("lock");
    public static final LockItem UN_LOCK_ITEM =new LockItem("un_lock");


    public LockItem(String id) {
        this.setRegistryName(Diminvo.MODID,id);
        this.setUnlocalizedName(id);
        EventHandler.ITEMS.put(id,this);
        this.setMaxStackSize(1);
    }
    public static void initialize(){

    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        return super.onDroppedByPlayer(item, player);
    }

    public static boolean isLock(Item item){
        return isLock(item,UN_LOCK_ITEM) || isLock(item,LOCK_ITEM);
    }

    public static boolean isLock(Item item0,LockItem item1){
        if (item0 ==null || item1 ==null){
            return false;
        }
        return item0.getRegistryName().getResourcePath().equals(item1.getRegistryName().getResourcePath());
    }
}

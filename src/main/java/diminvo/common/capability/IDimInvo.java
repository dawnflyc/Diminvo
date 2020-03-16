package diminvo.common.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:25
 **/
public interface IDimInvo extends NBTSerializable<IDimInvo> {

    void setInventory(NonNullList<ItemStack> list);

    NonNullList<ItemStack> getInventory();

    int getUnLockIndex();

    boolean setUnLockIndex(int unLockIndex);

    NonNullList<ItemStack> initialize();

    void format();

    boolean unNext();

}

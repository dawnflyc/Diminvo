package diminvo.common.capability;

import diminvo.common.item.LockItem;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:26
 **/
public class DimInvo implements IDimInvo {


    private NonNullList<ItemStack> inventory;

    private int unLockIndex;

    @Override
    public NonNullList<ItemStack> getInventory() {
        return this.inventory;
    }

    @Override
    public void setInventory(NonNullList<ItemStack> inventory) {
        this.inventory = inventory;
    }

    public int getUnLockIndex() {
        return unLockIndex;
    }

    public void setUnLockIndex(int unLockIndex) {
        this.unLockIndex = unLockIndex;
    }

    @Override
    public NonNullList<ItemStack> initialize() {
        this.inventory=NonNullList.withSize(54,ItemStack.EMPTY);
        this.unLockIndex=0;
        format();
        return this.inventory;
    }

    @Override
    public void format(){
        for (int i = 0; i < inventory.size(); i++) {
           if (i==unLockIndex){
                inventory.set(i,new ItemStack(LockItem.UN_LOCK_ITEM));
           }else if (i>unLockIndex){
               inventory.set(i,new ItemStack(LockItem.LOCK_ITEM));
           }else {
               String itemId=inventory.get(i).getItem().getRegistryName().getResourcePath();
               String unLockId=LockItem.UN_LOCK_ITEM.getRegistryName().getResourcePath();
               String lockId=LockItem.LOCK_ITEM.getRegistryName().getResourcePath();
               if (unLockId.equals(itemId) || lockId.equals(itemId)){
                   inventory.set(i,ItemStack.EMPTY);
               }
           }
        }
    }



    @Override
    public NBTBase writeNBT(Capability<IDimInvo> capability, EnumFacing side) {
        return ItemStackHelper.saveAllItems(new NBTTagCompound(),this.inventory);
    }


    @Override
    public void readNBT(Capability<IDimInvo> capability, EnumFacing side, NBTBase nbt) {
        initialize();
        ItemStackHelper.loadAllItems((NBTTagCompound) nbt,this.inventory);

    }
}

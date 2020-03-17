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

    public boolean setUnLockIndex(int unLockIndex) {
        if (unLockIndex<=inventory.size()) {
            this.unLockIndex = unLockIndex;
            format();
            return true;
        }
        return false;
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
               if (LockItem.isLock(this.inventory.get(i).getItem())){
                   inventory.set(i,ItemStack.EMPTY);
               }
           }
        }
    }

    @Override
    public boolean unNext() {
        return setUnLockIndex(getUnLockIndex()+1);
    }


    @Override
    public NBTBase writeNBT(Capability<IDimInvo> capability, EnumFacing side) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger("unLockIndex",this.unLockIndex);
        nbtTagCompound.setTag("inventory",ItemStackHelper.saveAllItems(new NBTTagCompound(),this.inventory));
        return nbtTagCompound;
    }


    @Override
    public void readNBT(Capability<IDimInvo> capability, EnumFacing side, NBTBase nbt) {
        initialize();
        NBTTagCompound nbtTagCompound= (NBTTagCompound) nbt;
        this.setUnLockIndex(nbtTagCompound.getInteger("unLockIndex"));
        ItemStackHelper.loadAllItems(nbtTagCompound.getCompoundTag("inventory"),this.inventory);
        format();
    }
}

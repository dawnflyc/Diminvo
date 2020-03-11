package diminvo.common.inventory;

import diminvo.common.capability.DimInvoProvider;
import diminvo.common.capability.IDimInvo;
import diminvo.common.item.LockItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class LockSlot extends Slot {
    public LockSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        if (LockItem.isLock(this.getStack().getItem())){
            IDimInvo dimInvo=playerIn.getCapability(DimInvoProvider.DIMINV,null);
            if (LockItem.isLock(this.getStack().getItem(),LockItem.UN_LOCK_ITEM) && this.slotNumber ==dimInvo.getUnLockIndex()){
                unLock(playerIn,dimInvo);
            }else {
                format(playerIn);
            }
            return false;
        }
        return true;
    }

    private void format(EntityPlayer player){
        player.getCapability(DimInvoProvider.DIMINV,null).format();
    }

    private void unLock(EntityPlayer player,IDimInvo dimInvo){
        dimInvo.setUnLockIndex(dimInvo.getUnLockIndex()+1);
        dimInvo.format();
    }


}

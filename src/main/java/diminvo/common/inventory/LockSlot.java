package diminvo.common.inventory;

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
        return !LockItem.isLock(this.getStack().getItem());
    }




}

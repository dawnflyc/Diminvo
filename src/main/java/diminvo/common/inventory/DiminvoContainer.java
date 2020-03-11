package diminvo.common.inventory;

import diminvo.common.capability.DimInvoProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:40
 **/
public class DiminvoContainer extends Container {

    private final DiminvoInventory chestInventory;

    public DiminvoContainer(EntityPlayer player) {
        this.chestInventory=new DiminvoInventory(player);

        chestInventory.openInventory(player);
        for (int j = 0; j < 6; ++j)
        {
            for (int k = 0; k < 9; ++k)
            {
                this.addSlotToContainer(new LockSlot(chestInventory, k + j * 9, 8 + k * 18, 18 + j * 18));

            }
        }

        for (int l = 0; l < 3; ++l)
        {
            for (int j1 = 0; j1 < 9; ++j1)
            {
                this.addSlotToContainer(new Slot(player.inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + 36));
            }
        }

        for (int i1 = 0; i1 < 9; ++i1)
        {
            this.addSlotToContainer(new Slot(player.inventory, i1, 8 + i1 * 18, 161 + 36));
        }

    }

    public IInventory getChestInventory() {
        return chestInventory;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.chestInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            int maxSlot=playerIn.getCapability(DimInvoProvider.DIMINV,null).getUnLockIndex();
            if (index < maxSlot)
            {
                if (!this.mergeItemStack(itemstack1, maxSlot, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, maxSlot, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }



}

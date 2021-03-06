package diminvo.client.gui;

import diminvo.Diminvo;
import diminvo.common.inventory.DiminvoContainer;
import diminvo.common.item.LockItem;
import diminvo.common.network.UnLockMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 05:29
 **/
@SideOnly(Side.CLIENT)
public class DiminvoGuiContainer extends GuiContainer
{
    private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
    public DiminvoGuiContainer(Container inventorySlotsIn) {
        super(inventorySlotsIn);
        this.ySize = 114 + 6 * 18;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, 6 * 18 + 17);
        this.drawTexturedModalRect(offsetX, offsetY + 6 * 18 + 17, 0, 126, this.xSize, 96);
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.fontRenderer.drawString(((DiminvoContainer)(this.inventorySlots)).getChestInventory().getName(), 8, 6, 0x404040);
    }

    @Override
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        if (slotIn !=null && LockItem.isLock(slotIn.getStack().getItem(),LockItem.UN_LOCK_ITEM)){
            if (type==ClickType.PICKUP){
                singleUnLock();
            }else if (type==ClickType.QUICK_MOVE){
                allUnLock();
            }
        }
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
    }

    private void singleUnLock(){
        EntityPlayerSP player= Minecraft.getMinecraft().player;
        if (player.experienceLevel<10 && !player.isCreative()) return;
        Diminvo.NETWORK.sendToServer(new UnLockMessage(UnLockMessage.UnLockMode.single));
        if (player.experienceLevel>=10 && !player.isCreative()){
            player.experienceLevel-=10;
        }
    }

    public void allUnLock(){
        EntityPlayerSP player= Minecraft.getMinecraft().player;
        if (player.experienceLevel<10 && !player.isCreative()) return;
        Diminvo.NETWORK.sendToServer(new UnLockMessage(UnLockMessage.UnLockMode.all));
    }
}

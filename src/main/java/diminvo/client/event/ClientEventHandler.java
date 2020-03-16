package diminvo.client.event;

import diminvo.Diminvo;
import diminvo.client.proxy.ClientProxy;
import diminvo.common.network.OpenInvoMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-21 16:29
 **/
@SideOnly(Side.CLIENT)
public class ClientEventHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){
        if (ClientProxy.KEY_BINDING_OPEN_INV.isPressed()){
            EntityPlayer player = Minecraft.getMinecraft().player;
            Diminvo.NETWORK.sendToServer(new OpenInvoMessage("diminv",new BlockPos(player.posX,player.posY,player.posZ)));
        }
    }
}

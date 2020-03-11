package diminvo.common.network;

import diminvo.Diminvo;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @program: DimensionInventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-26 22:15
 **/
public class DiminvoHandler implements IMessageHandler<DiminvoMessage,IMessage> {

    @Override
    public IMessage onMessage(DiminvoMessage message, MessageContext ctx) {
        BlockPos pos=message.getPos();
        ctx.getServerHandler().player.openGui(Diminvo.instance,message.getGuiId(),ctx.getServerHandler().player.world,pos.getX(),pos.getY(),pos.getZ());
        return null;
    }
}

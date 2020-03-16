package diminvo.common.network;

import diminvo.common.capability.DimInvoProvider;
import diminvo.common.capability.IDimInvo;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UnLockMessage implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
    /**
     * 解锁格子
     */
    public static class UnLock implements IMessageHandler<UnLockMessage, IMessage> {

        @Override
        public IMessage onMessage(UnLockMessage message, MessageContext ctx) {
            IDimInvo dimInvo= ctx.getServerHandler().player.getCapability(DimInvoProvider.DIMINV,null);
            if (dimInvo!=null && dimInvo.getInventory() !=null){
                if (ctx.getServerHandler().player.isCreative()){
                    dimInvo.unNext();
                }else if (ctx.getServerHandler().player.experienceLevel>=10){
                    ctx.getServerHandler().player.experienceLevel-=10;
                    dimInvo.unNext();
                }
            }
            return null;
        }
    }
}

package diminvo.common.network;

import diminvo.Diminvo;
import diminvo.common.capability.DimInvoProvider;
import diminvo.common.capability.IDimInvo;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class NetWorkHandler {
    /**
     * 按c进入gui
     */
    public static class OpenInvo implements IMessageHandler<OpenInvoMessage, IMessage>{

        @Override
        public IMessage onMessage(OpenInvoMessage message, MessageContext ctx) {
            BlockPos pos=message.getPos();
            ctx.getServerHandler().player.openGui(Diminvo.instance,message.getGuiId(),ctx.getServerHandler().player.world,pos.getX(),pos.getY(),pos.getZ());
            return null;
        }
    }

    /**
     * 解锁格子
     */
    public static class UnLock implements IMessageHandler<UnLockMessage, IMessage>{

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


    public static void initialize(){
        Diminvo.NETWORK.registerMessage(OpenInvo.class, OpenInvoMessage.class, 0, Side.SERVER);

        Diminvo.NETWORK.registerMessage(UnLock.class,UnLockMessage.class,1,Side.SERVER);
    }
}

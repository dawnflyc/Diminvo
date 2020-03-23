package diminvo.common.network;

import diminvo.common.capability.DimInvoProvider;
import diminvo.common.capability.IDimInvo;
import diminvo.common.inventory.DiminvoContainer;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class UnLockMessage implements IMessage {

    private int mode;

    public UnLockMessage() {
    }

    public UnLockMessage(UnLockMode mode) {
        this.mode = mode.code;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.mode=buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    buf.writeInt(mode);
    }

    public UnLockMode getMode() {
        return this.mode ==0 ? UnLockMode.single : this.mode == 1 ? UnLockMode.all : null;
    }

    public void setMode(UnLockMode mode) {
        this.mode = mode.code;
    }

    public static class UnLock implements IMessageHandler<UnLockMessage, IMessage> {

        @Override
        public IMessage onMessage(UnLockMessage message, MessageContext ctx) {
            IMessage returnMessage=null;
            IDimInvo dimInvo= ctx.getServerHandler().player.getCapability(DimInvoProvider.DIMINV,null);
            if (dimInvo!=null && dimInvo.getInventory() !=null){
                if (message.getMode()==UnLockMode.single){
                    single(ctx,dimInvo);
                }else {
                    int levelCount= all(ctx,dimInvo);
                    returnMessage= new returnMessage(levelCount);
                }
                EntityPlayerMP player= ctx.getServerHandler().player;
                if (player.openContainer!=null && player.openContainer instanceof DiminvoContainer){
                    player.sendAllContents(player.openContainer,dimInvo.getInventory());
                }

            }
            return returnMessage;
        }

        private void single(MessageContext ctx,IDimInvo dimInvo){
            if (ctx.getServerHandler().player.isCreative()){
                dimInvo.unNext();
            }else if (ctx.getServerHandler().player.experienceLevel>=10){
                ctx.getServerHandler().player.experienceLevel-=10;
                dimInvo.unNext();
            }
        }
        private int all(MessageContext ctx,IDimInvo dimInvo){
            if (ctx.getServerHandler().player.isCreative()){
                    dimInvo.setUnLockIndex(dimInvo.getInventory().size());
                return 0;
            }else{
                int count=0;
                for (int i = 0; i < dimInvo.getInventory().size(); i++) {
                 if (ctx.getServerHandler().player.experienceLevel>=10){
                    ctx.getServerHandler().player.experienceLevel-=10;
                    dimInvo.unNext();
                    count++;
                }else {
                     break;
                 }
                }
                dimInvo.format();
                return count;
            }
        }
    }

    public static enum UnLockMode{
        single(0),all(1);
        int code;

        UnLockMode(int code) {
            this.code = code;
        }
    }

    public static class returnMessage implements IMessage{


        private int unLockCount;

        @Override
        public void fromBytes(ByteBuf buf) {
            this.unLockCount=buf.readInt();
        }

        @Override
        public void toBytes(ByteBuf buf) {
        buf.writeInt(this.unLockCount);
        }

        public returnMessage(int unLockCount) {
            this.unLockCount = unLockCount;
        }

        public int getUnLockCount() {
            return unLockCount;
        }

        public void setUnLockCount(int unLockCount) {
            this.unLockCount = unLockCount;
        }

        public returnMessage() {
        }
    }
    public static class returnHandle implements IMessageHandler<returnMessage, IMessage>{

        @Override
        public IMessage onMessage(returnMessage message, MessageContext ctx) {
            Minecraft.getMinecraft().player.experienceLevel-=message.getUnLockCount()*10;
            return null;
        }
    }
}

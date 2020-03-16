package diminvo.common.network;

import diminvo.Diminvo;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * @program: DimensionInventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-26 22:02
 **/
public class OpenInvoMessage implements IMessage {

    private int guiId;

    private BlockPos pos;

    public OpenInvoMessage() {
    }

    public OpenInvoMessage(String guiId, BlockPos pos) {
        this.guiId = GuiHandler.getGuiId(guiId);
        this.pos = pos;
    }

    public int getGuiId() {
        return guiId;
    }

    public BlockPos getPos() {
        return pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiId=buf.readInt();
        this.pos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.guiId);
        buf.writeLong(pos.toLong());
    }
    /**
     * 按c进入gui
     */
    public static class OpenInvo implements IMessageHandler<OpenInvoMessage, IMessage> {

        @Override
        public IMessage onMessage(OpenInvoMessage message, MessageContext ctx) {
            BlockPos pos=message.getPos();
            ctx.getServerHandler().player.openGui(Diminvo.instance,message.getGuiId(),ctx.getServerHandler().player.world,pos.getX(),pos.getY(),pos.getZ());
            return null;
        }
    }
}

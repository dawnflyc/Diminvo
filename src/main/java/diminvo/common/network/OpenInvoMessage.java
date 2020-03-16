package diminvo.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

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
}

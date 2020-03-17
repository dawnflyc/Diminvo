package diminvo.common.network;

import diminvo.Diminvo;
import net.minecraftforge.fml.relauncher.Side;

public class NetWorkHandler {


    public static void initialize() {
        Diminvo.NETWORK.registerMessage(OpenInvoMessage.OpenInvo.class, OpenInvoMessage.class, 0, Side.SERVER);

        Diminvo.NETWORK.registerMessage(UnLockMessage.UnLock.class, UnLockMessage.class, 1, Side.SERVER);

        Diminvo.NETWORK.registerMessage(UnLockMessage.returnHandle.class,UnLockMessage.returnMessage.class,2,Side.CLIENT);
    }
}

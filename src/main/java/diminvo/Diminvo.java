package diminvo;

import diminvo.common.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:16
 **/
@Mod(modid = Diminvo.MODID, name = Diminvo.NAME, version = Diminvo.VERSION, dependencies = Diminvo.DEPEND, acceptedMinecraftVersions = "[1.12]")
public class Diminvo {
    /**
     * id
     */
    public static final String MODID = "diminvo";
    /**
     * name
     */
    public static final String NAME = "Diminvo";
    /**
     * 版本
     */
    public static final String VERSION = "1.0";
    /**
     * 前置
     */
    public static final String DEPEND = "";
    /**
     * 日志
     */
    public static final Logger LOGGER = LogManager.getLogger();

    public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(MODID);

    @SidedProxy(clientSide = "diminvo.client.proxy.ClientProxy", serverSide = "diminvo.common.proxyCommonProxy")
    public static CommonProxy PROXY;

    @Mod.Instance
    public static Diminvo instance;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }
}

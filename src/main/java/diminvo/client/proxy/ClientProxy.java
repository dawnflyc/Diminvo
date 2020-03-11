package diminvo.client.proxy;

import diminvo.client.event.ClientEventHandler;
import diminvo.common.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:19
 **/
public class ClientProxy extends CommonProxy {

    public static final KeyBinding KEY_BINDING_OPEN_INV=new KeyBinding("key.di.openinv", Keyboard.KEY_C,"key.categories.di");
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ClientRegistry.registerKeyBinding(KEY_BINDING_OPEN_INV);
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        ItemColors itemColors=Minecraft.getMinecraft().getItemColors();
    }
}

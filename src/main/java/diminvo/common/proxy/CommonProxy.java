package diminvo.common.proxy;

import diminvo.Diminvo;
import diminvo.client.gui.DiminvoGuiContainer;
import diminvo.common.capability.CapabilityHandler;
import diminvo.common.capability.DimInvo;
import diminvo.common.capability.DimInvoStorage;
import diminvo.common.capability.IDimInvo;
import diminvo.common.event.EventHandler;
import diminvo.common.inventory.DiminvoContainer;
import diminvo.common.item.LockItem;
import diminvo.common.network.GuiHandler;
import diminvo.common.network.IGuiElement;
import diminvo.common.network.NetWorkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import java.util.concurrent.Callable;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:19
 **/
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        //item
        LockItem.initialize();
        //gui
        NetworkRegistry.INSTANCE.registerGuiHandler(Diminvo.instance,new GuiHandler());
        //event
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        //capability
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        //capability init
        CapabilityManager.INSTANCE.register(IDimInvo.class, new DimInvoStorage(), new Callable<IDimInvo>() {
            @Override
            public IDimInvo call() throws Exception {
                return new DimInvo();
            }
        });
        //gui-diminv
        NetWorkHandler.initialize();

        GuiHandler.registry("diminv", new IGuiElement() {


            @Override
            public Object getServerGuiElement(EntityPlayer player, World world, int x, int y, int z) {
                return new DiminvoContainer(player);
            }

            @Override
            public Object getClientGuiElement(EntityPlayer player, World world, int x, int y, int z) {
                return new DiminvoGuiContainer(new DiminvoContainer(player));
            }
        });


    }

    public void init(FMLInitializationEvent event) {

    }
    public void postInit(FMLPostInitializationEvent event) {
    }
}

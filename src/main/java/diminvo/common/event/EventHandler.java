package diminvo.common.event;

import diminvo.common.capability.DimInvoProvider;
import diminvo.common.capability.IDimInvo;
import diminvo.common.item.LockItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:37
 **/
public class EventHandler {


    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event){
        if (event.getEntity() instanceof EntityPlayer) {
            initialize((EntityPlayer) event.getEntity());
        }
    }
    public static final Map<String,Item> ITEMS=new HashMap<>();

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        for (Item item : ITEMS.values()) {
            event.getRegistry().register(item);
            ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event){
        IDimInvo originalDimInvo=event.getOriginal().getCapability(DimInvoProvider.DIMINV,null);

        if (event.getOriginal().getEntityWorld().getGameRules().getBoolean("keepInventory")){
            initialize(event.getEntityPlayer());
            IDimInvo dimInvo= event.getEntityPlayer().getCapability(DimInvoProvider.DIMINV,null);
            dimInvo.setUnLockIndex(originalDimInvo.getUnLockIndex());
            dimInvo.setInventory(originalDimInvo.getInventory());
            return;
        }
        for (ItemStack itemStack : originalDimInvo.getInventory()) {
            if (!LockItem.isLock(itemStack.getItem())){
                World world=event.getOriginal().getEntityWorld();
                BlockPos pos=event.getOriginal().getPosition();
                world.spawnEntity(new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),itemStack));
            }
        }
    }

    public void initialize(EntityPlayer player){
            if (player.getCapability(DimInvoProvider.DIMINV,null)==null || player.getCapability(DimInvoProvider.DIMINV,null).getInventory()==null ){
                player.getCapability(DimInvoProvider.DIMINV,null).initialize();
            }
    }
}

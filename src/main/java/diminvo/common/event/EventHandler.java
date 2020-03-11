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
        if (event.getEntity() instanceof EntityPlayer){
            EntityPlayer player=(EntityPlayer)event.getEntity();
            if (player.getCapability(DimInvoProvider.DIMINV,null)==null || player.getCapability(DimInvoProvider.DIMINV,null).getInventory()==null ){
                player.getCapability(DimInvoProvider.DIMINV,null).initialize();
            }
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
        IDimInvo dimInvo=event.getOriginal().getCapability(DimInvoProvider.DIMINV,null);
        String unLockId= LockItem.UN_LOCK_ITEM.getRegistryName().getResourcePath();
        String lockId=LockItem.LOCK_ITEM.getRegistryName().getResourcePath();
        World world=event.getOriginal().getEntityWorld();
        BlockPos pos=event.getOriginal().getPosition();
        for (ItemStack itemStack : dimInvo.getInventory()) {
            String itemstackId=itemStack.getItem().getRegistryName().getResourcePath();
            if (!itemstackId.equals(unLockId) && !itemstackId.equals(lockId) && !itemStack.isEmpty()){
                world.spawnEntity(new EntityItem(world,pos.getX(),pos.getY(),pos.getZ(),itemStack));
            }
        }
    }

}

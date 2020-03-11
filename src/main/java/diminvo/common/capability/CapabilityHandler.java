package diminvo.common.capability;

import diminvo.Diminvo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:27
 **/
public class CapabilityHandler {
    public static final ResourceLocation DIMINV = new ResourceLocation(Diminvo.MODID, "diminv");


    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;
        event.addCapability(DIMINV, new DimInvoProvider());
    }
}

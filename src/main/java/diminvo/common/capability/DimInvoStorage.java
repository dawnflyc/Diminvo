package diminvo.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:27
 **/
public class DimInvoStorage implements Capability.IStorage<IDimInvo> {

    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IDimInvo> capability, IDimInvo instance, EnumFacing side) {
        return instance.writeNBT(capability,side);
    }

    @Override
    public void readNBT(Capability<IDimInvo> capability, IDimInvo instance, EnumFacing side, NBTBase nbt) {
        instance.readNBT(capability,side,nbt);
    }
}

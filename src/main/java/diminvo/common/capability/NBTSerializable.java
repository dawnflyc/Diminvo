package diminvo.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-21 15:33
 **/
public interface NBTSerializable<T> {

     NBTBase writeNBT(Capability<T> capability, EnumFacing side);

     void readNBT(Capability<T> capability, EnumFacing side, NBTBase nbt);
}

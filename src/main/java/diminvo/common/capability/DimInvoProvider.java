package diminvo.common.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @program: Dimension Inventory
 * @description:
 * @author: xiaofei
 * @create: 2020-02-16 04:27
 **/
public class DimInvoProvider implements ICapabilitySerializable<NBTBase> {

    @CapabilityInject(IDimInvo.class)
    public static final Capability<IDimInvo> DIMINV = null;

    private IDimInvo instance=DIMINV.getDefaultInstance();


    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability==DIMINV;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == DIMINV ? DIMINV.<T> cast(instance) : null;
    }

    @Override
    public NBTBase serializeNBT() {
        return DIMINV.getStorage().writeNBT(DIMINV,instance,null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        DIMINV.getStorage().readNBT(DIMINV, this.instance, null, nbt);
    }
}

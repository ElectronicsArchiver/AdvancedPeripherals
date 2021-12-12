package de.srendi.advancedperipherals.common.addons.mekanism;

import com.brandon3055.draconicevolution.blocks.tileentity.TileEnergyCore;
import dan200.computercraft.api.lua.LuaFunction;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.lib.peripherals.TileEntityIntegrationPeripheral;
import mekanism.api.math.FloatingLong;
import mekanism.common.content.network.transmitter.UniversalCable;
import mekanism.common.item.ItemNetworkReader;
import mekanism.common.tile.transmitter.TileEntityUniversalCable;
import net.minecraft.tileentity.TileEntity;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class UniversalCableIntegration extends TileEntityIntegrationPeripheral<TileEntityUniversalCable> {

    public UniversalCableIntegration(TileEntity entity) {
        super(entity);
    }

    @NotNull
    @Override
    public String getType() {
        return "universalCable";
    }

    @LuaFunction(mainThread = true)
    public int getTransmitters() {
        return tileEntity.getTransmitter().getTransmitterNetwork().transmittersSize();
    }

    @LuaFunction(mainThread = true)
    public int getAcceptors() {
        return tileEntity.getTransmitter().getTransmitterNetwork().getAcceptorCount();
    }

    @LuaFunction(mainThread = true)
    public long getThroughput() {
        try {
            Field field = tileEntity.getClass().getDeclaredField("prevTransferAmount");
            field.setAccessible(true);
            FloatingLong throughput = (FloatingLong) field.get(tileEntity);
            return throughput.getValue();
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            AdvancedPeripherals.debug("Could not access the throughout of the pylon! " + ex.getMessage(), Level.ERROR);
        }
        return 0;
    }

    @LuaFunction(mainThread = true)
    public long getStored() {
        return tileEntity.getTransmitter().buffer.getEnergy().getValue();
    }

    @LuaFunction(mainThread = true)
    public long getNetworkStored() {
        return tileEntity.getTransmitter().getTransmitterNetwork().getBuffer().getValue();
    }


    @LuaFunction(mainThread = true)
    public long getCapacity() {
        return tileEntity.getTransmitter().getCapacity();
    }

    @LuaFunction(mainThread = true)
    public long getNetworkCapacity() {
        return tileEntity.getTransmitter().getTransmitterNetwork().getCapacity();
    }

    @LuaFunction(mainThread = true)
    public String getTier() {
        return tileEntity.getTransmitter().tier.toString();
    }

}

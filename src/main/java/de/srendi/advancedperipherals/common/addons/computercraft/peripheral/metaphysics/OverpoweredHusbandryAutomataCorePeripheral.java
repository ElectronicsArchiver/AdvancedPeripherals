package de.srendi.advancedperipherals.common.addons.computercraft.peripheral.metaphysics;

import dan200.computercraft.api.turtle.ITurtleAccess;
import dan200.computercraft.api.turtle.TurtleSide;
import de.srendi.advancedperipherals.AdvancedPeripherals;
import de.srendi.advancedperipherals.common.addons.computercraft.operations.AutomataCoreTier;
import de.srendi.advancedperipherals.common.configuration.APConfig;

public class OverpoweredHusbandryAutomataCorePeripheral extends HusbandryAutomataCorePeripheral {

    public static final String TYPE = "overpoweredHusbandryAutomata";

    public OverpoweredHusbandryAutomataCorePeripheral(ITurtleAccess turtle, TurtleSide side) {
        super(TYPE, turtle, side, AutomataCoreTier.OVERPOWERED_TIER2);
        setAttribute(ATTR_STORING_TOOL_DURABILITY);
    }

    public void addRotationCycle(int count) {
        super.addRotationCycle(count);
        if (AdvancedPeripherals.RANDOM.nextDouble() <= APConfig.METAPHYSICS_CONFIG.OVERPOWERED_AUTOMATA_BREAK_CHANCE.get())
            owner.destroyUpgrade();
    }
}

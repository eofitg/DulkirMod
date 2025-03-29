package dulkirmod.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class ClientCommandBase extends CommandBase {
    private final String name;

    public ClientCommandBase(String name) {
        this.name = name;
    }

    @Override
    public String getCommandName() {
        return name;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + name;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
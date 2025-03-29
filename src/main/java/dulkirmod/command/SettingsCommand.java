package dulkirmod.command;

import dulkirmod.DulkirMod;
import dulkirmod.config.ConfigReader;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.*;

public class SettingsCommand extends ClientCommandBase {
    private static final String prefix = "[SwingSpeed]";

    public SettingsCommand() {
        super("swingspeed");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args == null || args.length == 0) {
            sendMessage("Usage: /swingspeed <toggle|speed|hastespeed|fatiguespeed|ignore>");
            return;
        }

        String command = args[0].toLowerCase(Locale.getDefault());
        switch (command) {
            case "toggle":
                boolean state = ConfigReader.toggleSwingSpeed();
                sendMessage("Swing Speed Mod: " + (state ? "Enabled" : "Disabled"));
                break;

            case "speed":
            case "hastespeed":
            case "fatiguespeed":
                if (args.length < 2) {
                    sendMessage("Usage: /swingspeed " + args[0] + " <value>");
                    return;
                }
                float value = 3f;
                try {
                    value = Float.parseFloat(args[1]);
                } catch (NumberFormatException e) {
                    sendMessage("Invalid value. Must be in [-2, 1].");
                    return;
                }
                if (value < -2 || value > 1) {
                    sendMessage("Invalid value. Must be in [-2, 1].");
                    return;
                }
                ConfigReader.setSpeed(args[0], value);
                sendMessage("Set " + args[0] + " to " + value);
                break;

            case "ignore":
                if (args.length < 2 || (!args[1].equals("haste") && !args[1].equals("fatigue"))) {
                    sendMessage("Usage: /swingspeed ignore <haste|fatigue>");
                    return;
                }
                boolean ignoreState = ConfigReader.toggleIgnore(args[1]);
                sendMessage("Ignore " + args[1] + ": " + (ignoreState ? "Enabled" : "Disabled"));
                break;

            default:
                sendMessage("Invalid parameter: " + args[0]);
                break;
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "toggle", "speed", "hastespeed", "fatiguespeed", "ignore");
        }

        if ("ignore".equalsIgnoreCase(args[0]) && args.length == 2) {
            return getListOfStringsMatchingLastWord(args, "haste", "fatigue");
        }

        return Collections.emptyList();
    }


    private void sendMessage(String message) {
        if (DulkirMod.getMc().thePlayer != null) {
            DulkirMod.getMc().thePlayer.addChatMessage(new ChatComponentText(prefix + " " + message));
        }
    }
}
package com.avisenera.minecraftbot.message;

import com.avisenera.minecraftbot.Formatting;
import com.avisenera.minecraftbot.MinecraftBot;
import com.avisenera.minecraftbot.hooks.Hook;

/**
 * Representation of a message to be relayed. Holds values that replace
 * certain words entered in the configuration file.
 */
public class Message {
    // To distinguish between where the messages are from, two different classes inherit
    // from this class. They are both exactly alike except for the name.
    protected Message() {}
    
    // Internal values
    // Assigning empty strings avoids NullPointerExceptions when using applyFormatting
    public String name = "";
    public String message = "";
    public String reason = "";
    public String channel = "";
    public String kicker = "";
    public String oldname = "";
    public String mode = "";
    
    // External values come from the Hook class and may depend on the internal values
    
    /**
     * Given a formatting string, replaces values such as %name% with their actual values.
     * @param formatting The formatting string to use
     * @param msg A Message object containing the values to replace with
     * @return A formatted string with variables replaced with the actual values
     */
    public static String applyFormatting(MinecraftBot p, String formatting, Message msg) {
        String fullmessage;
        
        // Formatting is reset at the end of each variable to
        // prevent formatting in one variable from spreading to the rest of the line
        fullmessage = formatting.replace("%name%", msg.name + Formatting.RESET);
        fullmessage = fullmessage.replace("%message%", msg.message + Formatting.RESET);
        fullmessage = fullmessage.replace("%reason%", msg.reason + Formatting.RESET);
        fullmessage = fullmessage.replace("%channel%", msg.channel + Formatting.RESET);
        fullmessage = fullmessage.replace("%kicker%", msg.kicker + Formatting.RESET);
        fullmessage = fullmessage.replace("%oldname%", msg.oldname + Formatting.RESET);
        fullmessage = fullmessage.replace("%mode%", msg.mode + Formatting.RESET);
        
        fullmessage = Hook.getVariable(p, fullmessage, msg);
        
        return fullmessage;
    }
}

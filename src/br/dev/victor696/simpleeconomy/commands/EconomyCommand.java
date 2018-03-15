package br.dev.victor696.simpleeconomy.commands;

import org.bukkit.entity.Player;

public abstract class EconomyCommand {
	
    private String command;
    private String permission;
    private String[] aliases;
    
    public EconomyCommand(final String command, final String permission, final String[] aliases) {
        this.command = command;
        this.permission = permission;
        this.aliases = aliases;
    }
    
    public String getCommand() {
        return this.command;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public String[] getAliases() {
        return this.aliases;
    }
    
    public abstract void onCommand(final Player p0, final String[] p1);
    
}

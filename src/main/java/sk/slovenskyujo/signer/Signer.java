package sk.slovenskyujo.signer;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import sk.slovenskyujo.signer.commands.SignCommand;

public final class Signer extends JavaPlugin {

    public static String translate (String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @Override
    public void onEnable() {

        getCommand("signer").setExecutor(new SignCommand());
    }
}

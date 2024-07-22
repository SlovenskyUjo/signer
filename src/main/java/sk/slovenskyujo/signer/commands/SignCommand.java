package sk.slovenskyujo.signer.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sk.slovenskyujo.signer.Signer;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("signer.usage")) {
                player.sendMessage(Signer.translate("&8[&cServer&8] &7No Permissions!"));
                return false;
            } else if (args.length == 0){
                List<String> msgs = new ArrayList<>();
                msgs.add(Signer.translate("&8--------- &eSignCommand &8--------- "));
                msgs.add(Signer.translate("&8• &f/signer sign"));
                msgs.add(Signer.translate("&8• &f/signer remove"));
                msgs.add(Signer.translate("&8--------- &eSignCommand &8--------- "));

                for (String msg : msgs) {
                    player.sendMessage(msg);
                }
            }

            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("sign")) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType().isAir()) {
                        player.sendMessage(Signer.translate("&8[&cServer&8] &7You must be holding an item to add lore!"));
                        return false;
                    }
                    ItemMeta meta = item.getItemMeta();
                    List<String> lore = meta.getLore();
                    if (lore == null || lore.isEmpty()) {
                        lore = new ArrayList<>();
                        lore.add(" ");
                    }
                    StringJoiner joiner = new StringJoiner(" ");
                    for (int i = 1; i < args.length; i++) {
                        joiner.add(ChatColor.translateAlternateColorCodes('&', args[i]));
                    }
                    lore.add(joiner.toString());
                    meta.setLore(lore);
                    item.setItemMeta(meta);

                    player.sendMessage(Signer.translate("&8[&cServer&8] &7Custom lore added to the item in your hand!"));
                    return true;
                }
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("remove")) {
                    ItemStack item = player.getInventory().getItemInMainHand();
                    if (item.getType().isAir()) {
                        player.sendMessage(Signer.translate("&8[&cServer&8] &7You must be holding an item to remove lore!"));
                        return false;
                    }

                    ItemMeta meta = item.getItemMeta();
                    if (meta == null || !meta.hasLore()) {
                        player.sendMessage(Signer.translate("&8[&cServer&8] &7This item has no lore"));
                        return true;
                    }

                    meta.setLore(null);
                    item.setItemMeta(meta);

                    player.sendMessage(Signer.translate("&8[&cServer&8] &7Lore has been deleted"));
                    return true;
                }
            }


        }

        return true;
    }
}

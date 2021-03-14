package me.hsgamer.essentialsbuy;

import com.earth2me.essentials.utils.NumberUtil;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static me.hsgamer.hscore.bukkit.utils.MessageUtils.sendMessage;

public class BuyWorthCommand extends BukkitCommand {
    private final IEssentials ess;

    public BuyWorthCommand() {
        super("buyworth", "Calculate the price of items", "/buyworth <item> [amount]", Collections.emptyList());
        this.ess = (IEssentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(getUsage());
            return false;
        }

        if (sender instanceof Player && !ess.getUser((Player) sender).isAuthorized("essentials.buyworth")) {
            sendMessage(sender, MessageConfig.NO_PERMISSION.getValue());
            return false;
        }

        ItemStack itemStack;
        try {
            itemStack = ess.getItemDb().get(args[0], 1);
        } catch (Exception e) {
            sendMessage(sender, MessageConfig.ITEM_NOT_FOUND.getValue());
            return false;
        }

        BigDecimal worth = ess.getWorth().getPrice(ess, itemStack);
        if (worth == null || worth.compareTo(BigDecimal.ZERO) < 0) {
            sendMessage(sender, MessageConfig.WORTH_NOT_FOUND.getValue());
            return false;
        }

        if (args.length > 1) {
            try {
                int amount = Integer.parseInt(args[1]);
                if (amount <= 0) {
                    sendMessage(sender, MessageConfig.POSITIVE_AMOUNT.getValue());
                    return false;
                }
                itemStack.setAmount(amount);
                worth = worth.multiply(BigDecimal.valueOf(amount));
            } catch (Exception e) {
                // IGNORED
            }
        }

        worth = worth.multiply(BigDecimal.valueOf(MainConfig.WORTH_MULTIPLIER.getValue()));
        sendMessage(sender, MessageConfig.BUY_WORTH_MESSAGE.getValue().replace("{item}", args[0]).replace("{amount}", String.valueOf(itemStack.getAmount())).replace("{money}", NumberUtil.displayCurrency(worth, ess)));
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String commandLabel, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(ess.getItemDb().listNames());
        } else if (args.length == 2) {
            return IntStream.rangeClosed(1, 64).mapToObj(String::valueOf).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}

package josscoder.vault.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.inventory.PlayerEnderChestInventory;
import com.nukkitx.fakeinventories.inventory.ChestFakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeInventory;
import com.nukkitx.fakeinventories.inventory.FakeInventoryListener;
import com.nukkitx.fakeinventories.inventory.event.FakeInventoryCloseEvent;
import com.nukkitx.fakeinventories.inventory.event.FakeSlotChangeEvent;

public class VaultCommand extends Command {

    public VaultCommand() {
        super("vault", "Vault Command", "/vault", new String[]{"ec", "enderchest"});
        setPermission("vault.command");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player) || !testPermission(commandSender)) {
            return false;
        }

        Player player = (Player) commandSender;

        ChestFakeInventory inventory = new ChestFakeInventory();
        inventory.setName(player.getName() + "'s EnderChest");

        PlayerEnderChestInventory enderChestInventory = player.getEnderChestInventory();
        inventory.setContents(enderChestInventory.getContents());

        inventory.addListener(new FakeInventoryListener() {
            @Override
            public void onSlotChange(FakeSlotChangeEvent fakeSlotChangeEvent) {

            }

            @Override
            public void onClose(FakeInventoryCloseEvent fakeInventoryCloseEvent) {
                FakeInventory fakeInventory = fakeInventoryCloseEvent.getInventory();
                enderChestInventory.setContents(fakeInventory.getContents());
            }
        });
        player.addWindow(inventory);

        return true;
    }
}

package josscoder.vault;

import cn.nukkit.plugin.PluginBase;
import josscoder.vault.command.VaultCommand;

public class JVaultPlugin extends PluginBase {

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("vault", new VaultCommand());
    }
}
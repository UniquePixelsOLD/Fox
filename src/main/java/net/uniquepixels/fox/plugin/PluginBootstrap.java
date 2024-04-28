package net.uniquepixels.fox.plugin;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginProviderContext;
import net.uniquepixels.fox.Fox;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class PluginBootstrap implements io.papermc.paper.plugin.bootstrap.PluginBootstrap {
    @Override
    public void bootstrap(@NotNull BootstrapContext context) {

    }

    @Override
    public @NotNull JavaPlugin createPlugin(@NotNull PluginProviderContext context) {
        return new Fox();
    }
}

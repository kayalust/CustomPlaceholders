package rip.kaya.placeholders;

/*
    โค้ดโดย kayalust @ Refine Development
    โปรเจ็ก: Placeholders
    วันที่: 1/4/2022
*/

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import rip.kaya.placeholders.command.CommandService;
import rip.kaya.placeholders.command.Drink;
import rip.kaya.placeholders.managers.MongoManager;
import rip.kaya.placeholders.managers.PlaceholderManager;

@Getter
public class PlaceholderPlugin extends JavaPlugin {

    @Getter
    private static PlaceholderPlugin instance;

    private MongoManager mongoManager;
    private PlaceholderManager placeholderManager;

    @Override
    public void onLoad() {
        instance = this;

        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        this.mongoManager = new MongoManager(this);
        mongoManager.init();

        this.placeholderManager = new PlaceholderManager(this);
        placeholderManager.init();

        CommandService drink = Drink.get(this);

        drink.bind(Placeholder.class).toProvider(new PlaceholderProvider());
        drink.register(new PlaceholderCommands(), "cp", "customplaceholders", "customplaceholder");

        drink.registerCommands();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlaceholderListener(), this);
    }

    @Override
    public void onDisable() {
        mongoManager.shutdown();

        instance = null;
    }
}
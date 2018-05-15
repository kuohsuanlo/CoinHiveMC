
package io.github.kuohsuanlo.coinhivemc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHivePlayerData;

public class CoinHiveMCPlugin extends JavaPlugin {

    
	public static CoinHiveMCRegularUpdate rlRegularUpdate ;
    public static int updaterId = -1;
    private final CoinHiveMCListener CHMCListener = new CoinHiveMCListener();

    
    public static String PREFIX ="[CoinHiveMC] : ";
    public static String SecretKey = "";
    public static int Verbosity = 0;
    public static long HashesPerCoin = 180000;
    public static boolean GriefpreventionBuildPermissionNeeded = true;
    public static boolean SpawnerEggNeedPermission = true;
    public static String NOT_TEAM_LEADER = "Not Leader";
    public static String NO_BUILD_PERMISSION = "You need build permission to do so.";
    public static String IS_PUBLIC = "You can't do this in a public claim.";
    public static String YOU_USED_COIN = "Success. You've used {coins}/{hashes}";
    public static String YOU_FAILED = "Fail. You don't have enough coins, or the internet is not reachable.";
    
    private static FileConfiguration config;
    private static CoinHiveMCCommand CommandExecutor ;

    public static final int EventLocationListMax = 100;
    public static ArrayList<String> enabledWorld;

    public static HashMap<String,CoinHivePlayerData> playerData = new HashMap<String,CoinHivePlayerData>();
    public static int UpdateFromCoinHivePeriodInSeconds = 20;
    
    @Override
    public void onDisable() {

        getLogger().info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));
    }
    public void onReload(){
    	
    	PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(CHMCListener, this);
         
        CommandExecutor = new CoinHiveMCCommand(this);
        getCommand("coinhivemc").setExecutor(CommandExecutor);
        
         
        reloadConfig();
     	config = getConfig();
     	loadConfig();

     	
    	startRoutines();
     	
    }
    @Override
    public void onEnable() {
    	if (!getDataFolder().exists()) {
    		getDataFolder().mkdirs();
            
        }
    	
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(CHMCListener, this);
        CommandExecutor = new CoinHiveMCCommand(this);
        getCommand("coinhivemc").setExecutor(CommandExecutor);
        if (pm.isPluginEnabled("PlaceholderAPI")) {
        	new AddPlaceholder(this).hook();
        } else {
            throw new RuntimeException(CoinHiveMCPlugin.PREFIX+"Could not find PlaceholderAPI!");
        }
      
    	config = getConfig();
    	config.addDefault("Verbosity",0);
    	config.addDefault("SpawnerEggNeedPermission",SpawnerEggNeedPermission);
    	config.addDefault("GriefpreventionBuildPermissionNeeded",GriefpreventionBuildPermissionNeeded);
    	
    	config.addDefault("PREFIX",PREFIX);
    	config.addDefault("SecretKey",SecretKey);
    	config.addDefault("HashesPerCoin",HashesPerCoin);
    	config.addDefault("NOT_TEAM_LEADER",NOT_TEAM_LEADER);
    	config.addDefault("NO_BUILD_PERMISSION",NO_BUILD_PERMISSION);
    	config.addDefault("IS_PUBLIC",IS_PUBLIC);
    	
    	config.addDefault("YOU_USED_COIN",YOU_USED_COIN);
    	config.addDefault("YOU_FAILED",YOU_FAILED);

    	
    	config.addDefault("UpdateFromCoinHivePeriodInSeconds",UpdateFromCoinHivePeriodInSeconds);
    	
    	
    	config.options().copyDefaults(true);
    	saveConfig();
    	loadConfig();
    	
    	
    	startRoutines();
    	
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
    }
    private void loadConfig(){

    	UpdateFromCoinHivePeriodInSeconds =config.getInt("UpdateFromCoinHivePeriodInSeconds");
    	Verbosity =config.getInt("Verbosity");
    	SpawnerEggNeedPermission = config.getBoolean("SpawnerEggNeedPermission");
    	GriefpreventionBuildPermissionNeeded = config.getBoolean("GriefpreventionBuildPermissionNeeded");
    	
    	PREFIX = config.getString("PREFIX");
    	SecretKey = config.getString("SecretKey");
    	HashesPerCoin = config.getLong("HashesPerCoin");
    	NOT_TEAM_LEADER = config.getString("NOT_TEAM_LEADER");
    	NO_BUILD_PERMISSION = config.getString("NO_BUILD_PERMISSION");
    	IS_PUBLIC = config.getString("IS_PUBLIC");
    	
    	YOU_USED_COIN = config.getString("YOU_USED_COIN");
    	YOU_FAILED = config.getString("YOU_FAILED");

    	
    }

    @SuppressWarnings("deprecation")
	private void startRoutines(){
    	if(updaterId>=0)
    		this.getServer().getScheduler().cancelTask(updaterId);
    	
    	rlRegularUpdate = null;
    	rlRegularUpdate = new CoinHiveMCRegularUpdate(this);
    	updaterId = this.getServer().getScheduler().scheduleAsyncRepeatingTask(this, rlRegularUpdate, 20, 20);
    }

}

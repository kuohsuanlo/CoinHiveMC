package io.github.kuohsuanlo.coinhivemc.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import me.ryanhamshire.GriefPrevention.Claim;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.md_5.bungee.api.ChatColor;

public class CoinHiveMCUtil {
	public static int pNumber = 10;
	public static double radius = 0.3;
	public static GriefPrevention gp = GriefPrevention.instance;
	
	public static ArrayList<String> accessTrust = new ArrayList<String>();
	public static ArrayList<String> containerTrust = new ArrayList<String>();
	public static ArrayList<String> buildTrust = new ArrayList<String>();
	public static ArrayList<String> managerTrust = new ArrayList<String>();
	
	public static ArrayList<String> getOwnerUUID(Claim claim){
		ArrayList<String> trustUUIDs = new ArrayList<String>();
		
		if(claim.getOwnerName()!=null){
			Player ofp = Bukkit.getPlayer(claim.getOwnerName());
			if(ofp!=null){
				trustUUIDs.add( ofp.getUniqueId().toString());
			}
		}
		return trustUUIDs;
	}
	private static void clearTrustList(){
		accessTrust.clear();
		containerTrust.clear();
		buildTrust.clear();
		managerTrust.clear();
	}
	public static boolean isClaimed(Claim c){
		if(c==null){
			if(CoinHiveMCPlugin.Verbosity>0){
				Bukkit.getServer().getConsoleSender().sendMessage(CoinHiveMCPlugin.PREFIX+"claim==null");
			}
			return false;
		}
		return true;
	}
	public static Claim getClaimFromLocation(Location location){
		Claim claim = gp.dataStore.getClaimAt(location, true, null);
		//no one's land
		if(claim==null){
			if(CoinHiveMCPlugin.Verbosity>0){
				Bukkit.getServer().getConsoleSender().sendMessage(CoinHiveMCPlugin.PREFIX+"claim==null");
			}
			return null;
		}
		return claim;
	}
	public static boolean isGPLoaded(){
		if(gp==null){
    		if(CoinHiveMCPlugin.Verbosity>0){
    			Bukkit.getServer().getConsoleSender().sendMessage(CoinHiveMCPlugin.PREFIX+"gp==null");
    		}
    		return false;
    	}
		return true;
	}
	public static boolean isPublicClaim(Claim claim){
		if(claim==null) return false;
		clearTrustList();
		claim.getPermissions(buildTrust, containerTrust, accessTrust , managerTrust);
		return(buildTrust.contains("public"));
	}
	public static boolean isPlayerInClaim(Player player, Claim claim){
		return claim.contains(player.getLocation(), true,false);
	}
	public static boolean isOwnerOfClaim(Player player, Claim claim){
		if(claim.ownerID==null) return false;
		return claim.ownerID.toString().equals(player.getUniqueId().toString());
	}
	
	public static ArrayList<String> getAllTrustUUID(Location location){
		
    	if(!isGPLoaded()) return new ArrayList<String>();
    	
		Claim claim = getClaimFromLocation(location);
		if(!isClaimed(claim)) return new ArrayList<String>();
		
		ArrayList<String> trustUUID = getOwnerUUID(claim);
		
		clearTrustList();
		claim.getPermissions(buildTrust, containerTrust, accessTrust , managerTrust);
		
		for(String uuid: accessTrust){
			trustUUID.add( uuid );
		}
		for(String uuid: containerTrust){
			trustUUID.add( uuid );
		}
		for(String uuid: buildTrust){
			trustUUID.add( uuid );
		}
		for(String uuid: managerTrust){
			trustUUID.add( uuid );
		}
		
		if(CoinHiveMCPlugin.Verbosity>0){
			for(String s : trustUUID){
				Bukkit.getServer().getConsoleSender().sendMessage(CoinHiveMCPlugin.PREFIX+"player names in trust : "+s);
			}
		}
		
    	return trustUUID;
    	
	}
    
    public static Inventory createUI(){
    	Inventory inv;
		inv = Bukkit.createInventory(null, 9*6,ChatColor.DARK_GREEN+CoinHiveMCPlugin.PREFIX);
		return inv;
	}
    public static void playNormalEffect(Location location){
		Location effectLocation = location.clone();
		Particle particle = Particle.TOTEM;
		location.getWorld().spawnParticle(particle, effectLocation, pNumber,radius,0,radius,0);
	}
    public static void playSuccessSoundEffect(Location location){
		Location effectLocation = location.clone();
		location.getWorld().playSound(effectLocation, Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.0f);
	}

	public static String getCoinNumber(long hashes){
		return new DecimalFormat("####.####").format(1.0*hashes/CoinHiveMCPlugin.HashesPerCoin);
		
	}
	public static void sendSuccessMessage(Player player, long spentHashes){
		String replaced = CoinHiveMCPlugin.YOU_USED_COIN+"";
		replaced = replaced.replace("{coins}", CoinHiveMCUtil.getCoinNumber(Long.valueOf(spentHashes)));
		replaced = replaced.replace("{hashes}", Long.valueOf(spentHashes)+"");
		player.sendMessage(CoinHiveMCPlugin.PREFIX+replaced);
	}
	public static void sendFailMessage(Player player, long spentHashes){
		player.sendMessage(CoinHiveMCPlugin.PREFIX+CoinHiveMCPlugin.YOU_FAILED);
	}
}

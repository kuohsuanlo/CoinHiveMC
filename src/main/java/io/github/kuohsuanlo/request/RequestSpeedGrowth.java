package io.github.kuohsuanlo.request;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.material.Crops;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;

public class RequestSpeedGrowth  extends RequestCoinHive{
	public static int pMax = Profession.values().length;
	public static Profession[] professions ;
	public static Random rng = new Random();
	public static int xDiff = 20;
	public static int yDiff = 20;
	public static int zDiff = 20;
	public RequestSpeedGrowth(Player p, CoinHiveMCPlugin pg) {
		super(p,pg);
	}
	@Override
	public void executeRequest() {
		super.executeRequest();
		for(int yd=-yDiff;yd<yDiff;yd++){
			Bukkit.getScheduler().runTaskLater(plugin, new growXZCrops(yd), yd/2L);
		}
	}
	public static boolean isGrowable(Block block){
		return block.getType().equals(Material.CARROT)  ||
				 block.getType().equals(Material.POTATO)  ||
				 block.getType().equals(Material.CROPS)  ||
				 block.getType().equals(Material.BEETROOT_BLOCK) ||
			     block.getType().equals(Material.NETHER_WARTS) ;
	}
	class growXZCrops implements Runnable{
		public final int yd;
		public growXZCrops(int yd){
			this.yd =yd;
		}
	    public void run(){
	 	   for(int xd=-xDiff;xd<xDiff;xd++){
				for(int zd=-zDiff;zd<zDiff;zd++){
					Block block = player.getLocation().clone().add(xd,yd,zd).getBlock();
					if(isGrowable(block)){
						ripeCrops(block);
						CoinHiveMCUtil.playNormalEffect(block.getLocation());
					}
				}
	   		}
	    }
	}

	public void ripeCrops(Block block){
		Crops crops = (Crops) block.getState().getData();
		crops.setState(CropState.RIPE);
	}
}


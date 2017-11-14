package io.github.kuohsuanlo.request;

import java.util.Random;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.material.Crops;

public class RequestSpeedGrowth  extends RequestCoinHive{
	public static int pMax = Profession.values().length;
	public static Profession[] professions ;
	public static Random rng = new Random();
	public static int xDiff = 20;
	public static int yDiff = 20;
	public static int zDiff = 20;
	public RequestSpeedGrowth(Player p) {
		super(p);
	}
	@Override
	public void executeRequest() {
		for(int xd=-xDiff;xd<xDiff;xd++){
			for(int yd=-yDiff;yd<yDiff;yd++){
				for(int zd=-zDiff;zd<zDiff;zd++){
					Block block = player.getLocation().add(xd,yd,zd).getBlock();
					if(isCrop(block)){
						Crops crops = (Crops) block.getState().getData();
						crops.setState(CropState.SEEDED);
					}
				}
			}
		}
	}
	public static boolean isCrop(Block block){
		return block.getType().equals(Material.CARROT)  ||
				 block.getType().equals(Material.POTATO)  ||
				 block.getType().equals(Material.CROPS)  ||
				 block.getType().equals(Material.BEETROOT_BLOCK) ;
	}

}

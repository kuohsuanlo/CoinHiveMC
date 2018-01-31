package io.github.kuohsuanlo.coinhivemc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import io.github.kuohsuanlo.coinhivemc.util.CoinHiveMCUtil;
import io.github.kuohsuanlo.coinhivemc.util.CoinHiveWebUtil;
import io.github.kuohsuanlo.request.RequestCoinHive;
import net.md_5.bungee.api.chat.TextComponent;

public class CoinHiveMCRegularUpdate implements Runnable{
	
	
	public long serverTime=0;
	public ArrayList<RequestCoinHive> RequestList = new ArrayList<RequestCoinHive>();
	public CoinHiveMCRegularUpdate(){
		Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+CoinHiveMCPlugin.PREFIX+"starts updating from coinhive!");
	
	}
    public void run() {
    	long currentTime = System.currentTimeMillis();
    	if((currentTime - serverTime)/1000 > CoinHiveMCPlugin.UpdateFromCoinHivePeriodInSeconds){
    		serverTime = currentTime;
    		CoinHiveWebUtil.updateLocalAllPlayerDataFromWeb();
    	}
    	processRequestList();
    
	}
    public void processRequestList(){
    	if(RequestList.size()>0){
			for(RequestCoinHive r : RequestList){
				r.executeRequest();
			}
			RequestList.clear();
    	}
    }
	
}


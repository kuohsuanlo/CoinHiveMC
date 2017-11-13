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
import net.md_5.bungee.api.chat.TextComponent;

public class CoinHiveMCRegularUpdate implements Runnable{
	
	
	public long serverTime=0;
	
	private CoinHiveMCPlugin fplugin;
	public CoinHiveMCRegularUpdate(CoinHiveMCPlugin plugin){
		fplugin = plugin;
		fplugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+CoinHiveMCPlugin.PREFIX+"starts updating from coinhive!");
	
	}
    public void run() {
    	long currentTime = System.currentTimeMillis();
    	if((currentTime - serverTime)/1000 > CoinHiveMCPlugin.UpdateFromCoinHivePeriodInSeconds){
    		
    		serverTime = currentTime;
    		
    		String url = "https://api.coinhive.com/user/list?secret=14enoIzcm2zjdTO4E59nrsnXmRxdKvYB";
            try {
            	InputStream is  = new URL(url).openStream();
                 BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); 
                 StringBuilder sb = new StringBuilder();
                 int cp;
                 while ((cp = rd.read()) != -1) {
                     sb.append((char) cp);
                 }
                
                 System.out.println(sb.toString());
                 is.close();
            } catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
        
    	}
    	
    	
	}
	
}


package io.github.kuohsuanlo.coinhivemc.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.github.kuohsuanlo.coinhivemc.CoinHiveMCPlugin;
import net.md_5.bungee.api.ChatColor;

public class CoinHiveWebUtil {
	public static JSONParser parser = new JSONParser();
	public static JSONObject json = new JSONObject();
	public static void updateLocalPlayerDataFromWeb(){
        json = getJSONObjectFromURL(getCoinHiveListURL(),"GET");
        updateLocalAllPlayersData((JSONArray) json.get("users"));
           
	}
	public static boolean withdrawBalance(String playername,long amount){
		json = getJSONObjectFromWithdrawRequest(getCoinHiveWithdrawURL(),playername, amount);
		boolean success = Boolean.getBoolean(json.get("success").toString());
		if(success){
			Bukkit.getServer().getConsoleSender().sendMessage(
					ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+json.toJSONString());
			updateLocalPlayerData(json);
		}
		else{
			Bukkit.getServer().getConsoleSender().sendMessage(
					ChatColor.RED+CoinHiveMCPlugin.PREFIX+json.toJSONString());
		}
		return success;
	}

	private static JSONObject getJSONObjectFromWithdrawRequest(String urlString, String playername, long amount){
		String urlParameters  = "&name="+playername+"&amount="+amount;
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection  = setupHTTPConnection(url,"POST");
			connection.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			return  (JSONObject) parser.parse(response.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	private static JSONObject getJSONObjectFromURL(String urlString,String requestType){
		
	    try { 
	    	URL url = new URL(urlString);
	    	HttpURLConnection connection = setupHTTPConnection(url,requestType);
	    	connection.connect();
	    	
	    	InputStream is  = url.openStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); 
	        StringBuilder sb = new StringBuilder();
	        
	        int cp;
	        while ((cp = rd.read()) != -1) {
	            sb.append((char) cp);
	        }
	        is.close();
	        return (JSONObject) parser.parse(sb.toString());
	
	    } catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void updateLocalPlayerData(JSONObject user){
    	CoinHivePlayerData data = new CoinHivePlayerData(user);
    	CoinHiveMCPlugin.playerData.put(user.get("name").toString(), data);
    	if(CoinHiveMCPlugin.Verbosity>=1){
    		Bukkit.getServer().getConsoleSender().sendMessage(
    						ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+data.toString());
    	}
	}
	private static void updateLocalAllPlayersData(JSONArray users){
		for(int i=0;i<users.size();i++){ 
        	JSONObject user = (JSONObject) users.get(i);
        	CoinHivePlayerData data = new CoinHivePlayerData(user);
        	
        	CoinHiveMCPlugin.playerData.put(user.get("name").toString(), data);
        	
        	if(CoinHiveMCPlugin.Verbosity>=2){
        		Bukkit.getServer().getConsoleSender().sendMessage(
        						ChatColor.GREEN+CoinHiveMCPlugin.PREFIX+data.toString());
        	}
        	
        }
	}
	private static String getCoinHiveListURL(){
		String key = CoinHiveMCPlugin.SecretKey;
		return "https://api.coinhive.com/user/list?secret="+key;
	}
	private static String getCoinHiveWithdrawURL(){
		String key = CoinHiveMCPlugin.SecretKey;
		return "https://api.coinhive.com/user/withdraw?secret="+key;
		
	}
	private static HttpURLConnection setupHTTPConnection(URL url, String requestType) throws IOException{
		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000); 
		connection.setReadTimeout(5000 );
		connection.setRequestMethod(requestType);
		return connection;
	}
	
	
}

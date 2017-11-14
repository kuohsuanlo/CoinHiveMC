package io.github.kuohsuanlo.coinhivemc.util;

import org.json.simple.JSONObject;

public class CoinHivePlayerData {
	public final String name;
	public final long total;
	public final long balance;
	public final long withdrawn;

	public CoinHivePlayerData(JSONObject user){
		name = user.get("name").toString();
		total = Long.valueOf( user.get("total").toString());
		balance= Long.valueOf( user.get("balance").toString());
		withdrawn = Long.valueOf( user.get("withdrawn").toString());
	}
	
	@Override
	public String toString() {
		return "{" + name + "::" + total + "::" + balance + "::" + withdrawn +"}";
	}
}

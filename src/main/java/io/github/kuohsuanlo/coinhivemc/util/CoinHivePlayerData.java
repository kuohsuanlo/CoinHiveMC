package io.github.kuohsuanlo.coinhivemc.util;

import org.json.simple.JSONObject;

public class CoinHivePlayerData {
	public final String name;
	public final long total;
	public long balance;

	public CoinHivePlayerData(JSONObject user){
		name = user.get("name").toString();
		total = Long.valueOf( user.get("total").toString());
		balance= Long.valueOf( user.get("balance").toString());
	}
	
	@Override
	public String toString() {
		return "{" + name + "::" + total + "::" + balance + "::" +"}";
	}
}

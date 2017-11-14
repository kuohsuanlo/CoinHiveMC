package io.github.kuohsuanlo.coinhivemc.util;

import org.json.simple.JSONObject;

public class CoinHivePlayerData {
	public final String name;
	public final long total;
	public long balance;
	public long withdraw;

	public CoinHivePlayerData(JSONObject user){
		name = user.get("name").toString();
		total = Long.valueOf( user.get("total").toString());
		balance= Long.valueOf( user.get("balance").toString());
		balance= Long.valueOf( user.get("withdraw").toString());
	}
	
	@Override
	public String toString() {
		return "{" + name + "::" + total + "::" + balance + "::" +"}";
	}
}

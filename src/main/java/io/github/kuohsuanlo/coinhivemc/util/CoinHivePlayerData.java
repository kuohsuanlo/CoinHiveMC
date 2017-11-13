package io.github.kuohsuanlo.coinhivemc.util;

public class CoinHivePlayerData {
	private String uuid;
	private String name;
	private String donateTime;
	private String donateNTD;
	private String vipMonth;

	public boolean isValidSub;
	
	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDonateTime() {
		return donateTime;
	}


	public void setDonateTime(String donateTime) {
		this.donateTime = donateTime;
	}


	public String getDonateNTD() {
		return donateNTD;
	}


	public void setDonateNTD(String donateNTD) {
		this.donateNTD = donateNTD;
	}


	public String getVipMonth() {
		return vipMonth;
	}


	public void setVipMonth(String vipMonth) {
		this.vipMonth = vipMonth;
	}


	@Override
	public String toString() {
		return "{" + name + "::" + uuid + "::" + donateTime + "::" + donateNTD + "::"+vipMonth+"}";
	}
}

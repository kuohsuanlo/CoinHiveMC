package io.github.kuohsuanlo.coinhivemc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONfromURL {
	public static void main(String[] argv){
		System.out.println("Enter key");
		String key = (new Scanner(System.in)).nextLine();
		String url = "https://api.coinhive.com/user/list?secret="+key;
		
        try {
        	InputStream is  = new URL(url).openStream();
             BufferedReader rd = new BufferedReader(new InputStreamReader(is,"utf-8")); 
             StringBuilder sb = new StringBuilder();
             int cp;
             while ((cp = rd.read()) != -1) {
                 sb.append((char) cp);
             }
             JSONParser parser = new JSONParser();
             JSONObject json = (JSONObject) parser.parse(sb.toString());
             JSONArray users = (JSONArray) json.get("users");
             for(int i=0;i<users.size();i++){ 
            	 System.out.println(users.get(i).toString());
             }
             
             is.close();
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
	}
}

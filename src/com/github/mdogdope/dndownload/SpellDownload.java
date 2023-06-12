package com.github.mdogdope.dndownload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SpellDownload {
	
	Vector<SpellLinks> links = new Vector<>();
	
	public SpellDownload() {
		
	}
	
	/**
	 * Reads the links from files and sets them to the links object. Used for debugging.
	 * @throws IOException
	 */
	public void readLinks() throws IOException {
		for(int i = 0; i <= 9; i++) {
			BufferedReader br = new BufferedReader(new FileReader(new File("links/Spell" + i + "Links.json")));
			
			String json = "";
			
			while(br.ready()) {
				json += br.readLine();
			}
			
			Gson gson = new Gson();
			SpellLinks sl = gson.fromJson(json, SpellLinks.class);
			this.links.add(sl);
			
			br.close();
		}
	}
	
	public void fetchSpells() {
		
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	public void fetchLinks() throws IOException {
		String URL = "http://dnd5e.wikidot.com/spells";
		
		Document doc = Jsoup.connect(URL).timeout(0).get();
		for(int i = 0; i <= 9 ; i++) {
			
			Element table = doc.getElementById("wiki-tab-0-" + i);
			Elements names = table.getElementsByTag("a");
			
			SpellLinks sl = new SpellLinks();
			
			for(Element e : names) {
				String link = e.attr("href");
				sl.addLink(link);
			}
			
			this.links.add(sl);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(sl).indent(4);
			
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("links/Spell" + i + "Links.json")));
			br.write(json);
			br.close();
		}
	}
}

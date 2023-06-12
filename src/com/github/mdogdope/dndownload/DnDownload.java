package com.github.mdogdope.dndownload;

import java.io.IOException;

public class DnDownload {

	public static void main(String[] args) {
		try {
			System.out.println("Welcome to DnDownload!");
			System.out.println("What would you like to do?");
			System.out.println("1. Download Spells.");
			
			
			System.out.println("Starting the download.");
			
			SpellDownload sdl = new SpellDownload();
			
			sdl.fetchLinks();
//			sdl.readLinks();
			sdl.fetchSpells();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

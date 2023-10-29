package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.village.TradeOfferList;
import net.minecraft.text.Text;
import net.minecraft.screen.MerchantScreenHandler;

import java.io.FileWriter;
import java.io.IOException;

public class VillagerTradeDumperClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		// Register the onOpenScreen method to be called when a Screen
		// is opened.
		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof MerchantScreen) {
				System.out.println("MerchantScreen opened");
			}
		});
		System.out.println("VillagerTradeDumperClient loaded");
	}

	private static void dumpTrades(MerchantScreen merchantScreen) {
		// use the mixed in MerchantScreen.getOffers() to get the TradeOfferList
		MerchantScreenHandler merchantScreenHandler = (MerchantScreenHandler) merchantScreen.getScreenHandler();
		TradeOfferList tradeOfferList = merchantScreenHandler.getRecipes();

		// list the trades in the console and to C:\tmp\trades.txt
		System.out.println("Trades:");
		for (int i = 0; i < tradeOfferList.size(); i++) {
			System.out.println("Trade " + i + ": " + tradeOfferList.get(i).toString());
		}
		try {
			FileWriter fileWriter = new FileWriter("C:\\tmp\\trades.txt");
			fileWriter.write("Trades:\n");
			for (int i = 0; i < tradeOfferList.size(); i++) {
				fileWriter.write("Trade " + i + ": " + tradeOfferList.get(i).toString() + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.village.TradeOfferList;
import net.minecraft.text.Text;
import net.minecraft.screen.MerchantScreenHandler;

import java.io.FileWriter;
import java.io.IOException;

public class VillagerTradeDumperClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		System.out.println("VillagerTradeDumperClient loaded");
		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof MerchantScreen) {
				dumpTrades((MerchantScreen) screen);
			}
		});
	}

	private static void dumpTrades(MerchantScreen merchantScreen) {
		// use the mixed in MerchantScreen.getOffers() to get the TradeOfferList
		MerchantScreenHandler merchantScreenHandler = (MerchantScreenHandler) merchantScreen.getScreenHandler();
		TradeOfferList tradeOfferList = merchantScreenHandler.getRecipes();

		// list the trades in the console and to the chat and to C:\tmp\trades.txt
		System.out.println("Trades:");
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.of("Trades:"));
		for (int i = 0; i < tradeOfferList.size(); i++) {
			System.out.println("Trade " + i + ": " + tradeOfferList.get(i).toString());
			MinecraftClient.getInstance().inGameHud.getChatHud()
					.addMessage(Text.of("Trade " + i + ": " + tradeOfferList.get(i).toString()));
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
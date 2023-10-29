package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.village.TradeOfferList;
import net.minecraft.text.Text;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.village.Merchant;

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
				dumpTrades((MerchantScreen) screen);
			}
		});
		System.out.println("VillagerTradeDumperClient loaded");
	}

	private static void dumpTrades(MerchantScreen merchantScreen) {
		// TODO
	}
}
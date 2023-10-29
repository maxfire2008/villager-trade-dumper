package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOfferList;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;

import net.maxstuff.villagertradedumper.mixin.client.MerchantScreenHandlerAccessor;

public class VillagerTradeDumperClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof MerchantScreen) {
				dumpTrades((MerchantScreen) screen);
			}
		});
		System.out.println("VillagerTradeDumperClient loaded");
	}

	private static void dumpTrades(MerchantScreen merchantScreen) {
		MerchantScreenHandler handler = merchantScreen.getScreenHandler();
		Merchant merchant = ((MerchantScreenHandlerAccessor) handler).getMerchant();
		TradeOfferList tradeOffers = merchant.getOffers();

		System.out.println("Trade offers:");

		for (TradeOffer tradeOffer : tradeOffers) {
			ItemStack firstBuyItem = tradeOffer.getOriginalFirstBuyItem();
			ItemStack secondBuyItem = tradeOffer.getSecondBuyItem();
			ItemStack sellItem = tradeOffer.getSellItem();

			System.out.println("  " + firstBuyItem.getCount() + "x " + firstBuyItem.getName().getString() + " + "
					+ secondBuyItem.getCount() + "x " + secondBuyItem.getName().getString() + " -> "
					+ sellItem.getCount() + "x " + sellItem.getName().getString());
		}
	}
}
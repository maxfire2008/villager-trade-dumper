package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;
import net.minecraft.screen.MerchantScreenHandler;

import net.maxstuff.villagertradedumper.IMerchantScreenHandler;

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
		IMerchantScreenHandler iHandler = (IMerchantScreenHandler) handler;
		Merchant merchant = iHandler.getMerchant();

		// print the merchant's current customer's name
		PlayerEntity customer = merchant.getCustomer();
		if (customer != null) {
			System.out.println("Customer: " + customer.getName().getString());
		} else {
			System.out.println("Customer: none");
		}

		Integer experience = merchant.getExperience();
		System.out.println("Experience: " + experience);

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
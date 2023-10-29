package net.maxstuff.villagertradedumper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TraderInventory;
import net.minecraft.village.VillagerData;
import net.minecraft.village.VillagerProfession;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VillagerTradeDumperClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as
		// rendering.
		MerchantScreen merchantScreen = (MerchantScreen) Screens.getScreen();
		if (merchantScreen != null) {
			VillagerEntity villager = merchantScreen.getMerchant();
			if (villager instanceof TraderInventory) {
				// Check if the "dump" button is pressed and log trades.
				if (merchantScreen.getDumpButton().isMouseOver(merchantScreen.client.mouse.getX(),
						merchantScreen.client.mouse.getY())) {
					logTrades(villager);
				}
			}
		}

		MerchantScreenHooks.registerTradeDumpButton();
	}

}

private void logTrades(VillagerEntity villager) {
    List<TradeOffer> tradeOffers = new ArrayList<>();
    VillagerData villagerData = villager.getVillagerData();
    VillagerProfession profession = villagerData.getProfession();

    for (TradeOffer trade : villager.getOffers()) {
        tradeOffers.add(trade);
    }

    // Create a file and write the trade data to it.
    Path logFilePath = MinecraftClient.getInstance().runDirectory.toPath().resolve("trade_log.txt");
    try (FileWriter writer = new FileWriter(logFilePath.toFile(), true)) {
        writer.write("Villager: " + profession.toString() + "\n");
        for (TradeOffer tradeOffer : tradeOffers) {
            writer.write("Input: " + tradeOffer.getOriginalFirstBuyItem().getTranslationKey() + "\n");
            writer.write("Output: " + tradeOffer.getOriginalSecondBuyItem().getTranslationKey() + "\n");
            writer.write("\n");
        }
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

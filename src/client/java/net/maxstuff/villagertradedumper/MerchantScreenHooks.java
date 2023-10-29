package net.maxstuff.villagertradedumper;

import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class MerchantScreenHooks {
    public static void registerTradeDumpButton() {
        Screens.registerScreenFactory(new Identifier("fabric-tradelogger", "merchant_screen"),
                (screen, inventory, title) -> {
                    ButtonWidget dumpButton = new ButtonWidget(0, 0, 20, 20, new TranslatableText("Dump"), (button) -> {
                        // Handle button click here.
                    });
                    ((MerchantScreen) screen).addButton(dumpButton);
                });
    }
}
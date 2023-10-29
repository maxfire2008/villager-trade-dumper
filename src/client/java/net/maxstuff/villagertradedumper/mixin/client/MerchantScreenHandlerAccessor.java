package net.maxstuff.villagertradedumper.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.village.Merchant;

@Mixin(net.minecraft.screen.MerchantScreenHandler.class)
public interface MerchantScreenHandlerAccessor {
    @Accessor("merchant")
    Merchant getMerchant();
}
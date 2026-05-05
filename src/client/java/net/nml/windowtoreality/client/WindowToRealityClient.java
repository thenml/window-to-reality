package net.nml.windowtoreality.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class WindowToRealityClient implements ClientModInitializer {
	public static boolean CLEAR_SKY = false;
	@Override
	public void onInitializeClient() {
		if (FabricLoader.getInstance().isModLoaded("fabric-api")) {
			ClientRegistry.init();
		}
	}
}
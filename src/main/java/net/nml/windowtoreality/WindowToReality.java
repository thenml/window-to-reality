package net.nml.windowtoreality;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowToReality implements ModInitializer {
	public static final String MOD_ID = "window-to-reality";
	@SuppressWarnings("null")
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	public static Identifier of(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}

	public static boolean CLEAR_SKY = booleanProperty("WTR_CLEAR_SKY");

	private static boolean booleanProperty(final String name) {
		String value = System.getProperty(name);
		return value != null && (value.isEmpty() || Boolean.parseBoolean(value));
	}

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("fabric-api")) {
			ModRegistry.init();
		}
	}
}
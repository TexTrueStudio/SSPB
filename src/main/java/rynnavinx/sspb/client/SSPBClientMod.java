package rynnavinx.sspb.client;

//import net.fabricmc.api.ClientModInitializer;
//import net.fabricmc.api.EnvType;
//import net.fabricmc.api.Environment;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rynnavinx.sspb.client.gui.SSPBGameOptions;
import rynnavinx.sspb.reflection.ReflectionAoFaceData;
import rynnavinx.sspb.reflection.ReflectionSmoothLightPipeline;

@Mod(SSPBClientMod.MODID)
@OnlyIn(Dist.CLIENT)
public class SSPBClientMod {
	public static final String MODID = "sspb";
	public static Logger LOGGER = LogManager.getLogger("SSPB");
	private static SSPBGameOptions CONFIG;


	public static SSPBGameOptions options() {
		return CONFIG;
	}

	public SSPBClientMod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onInitializeClient);

		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

	}

	//@Override
	public void onInitializeClient(final FMLClientSetupEvent event) {
		try {
			ReflectionAoFaceData.InitReflectionAoFaceData();
			ReflectionSmoothLightPipeline.InitReflectionSmoothLightPipeline();

			CONFIG = SSPBGameOptions.load();
			CONFIG.updateShadowyness(CONFIG.shadowynessPercent);

			LOGGER.info("[SSPB] Broken dirt path lighting is best dirt path lighting lol");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}

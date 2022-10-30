package skyhelper.networth.constants;

import java.util.HashMap;
import java.util.Map;

public class ApplicationWorth {

	public static Map<String, Double> applicationWorth = new HashMap<>() {
		{
			put("essence", 0.75);
			put("prestigeItem", 1.0);
			put("winningBid", 1.0);
			put("silex", 0.75);
			put("enchants", 0.85);
			put("woodSingularity", 0.5);
			put("tunedTransmission", 0.7);
			put("hotPotatoBook", 1.0);
			put("fumingPotatoBook", 0.6);
			put("dye", 0.9);
			put("artOfWar", 0.6);
			put("artOfPeace", 0.8);
			put("farmingForDummies", 0.5);
			put("enrichment", 0.75);
			put("recomb", 0.8);
			put("gemstone", 1.0);
			put("reforge", 1.0);
			put("masterStar", 1.0);
			put("necronBladeScroll", 1.0);
			put("gemstoneChamber", 0.9);
			put("drillPart", 1.0);
			put("etherwarp", 1.0);
			put("thunderInABottle", 0.8);
			put("runes", 0.6);
			put("petItem", 1.0);
			put("petCandy", 0.65);
		}
	};

	public static Map<String, Double> enchantsWorth = Map.of(
		"overload",
		0.35,
		"ultimate_soul_eater",
		0.35,
		"ultimate_inferno",
		0.35,
		"ultimate_fatal_tempo",
		0.35,
		"big_brain",
		0.35,
		"counter_strike",
		0.2
	);
}

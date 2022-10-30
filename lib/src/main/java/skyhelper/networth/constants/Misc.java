package skyhelper.networth.constants;

import java.util.List;
import java.util.Map;

public class Misc {

	public static final Map<String, List<String>> blockedEnchants = Map.of(
		"bone_boomerang",
		List.of("overload", "power", "ultimate_soul_eater"),
		"death_bow",
		List.of("overload", "power", "ultimate_soul_eater")
	);
	public static final Map<String, Integer> ignoredEnchants = Map.of("scavenger", 5);
	public static final String[] stackingEnchants = { "expertise", "compact", "cultivating", "champion", "hecatomb" };
	public static final String[] masterStars = {
		"first_master_star",
		"second_master_star",
		"third_master_star",
		"fourth_master_star",
		"fifth_master_star",
	};
	public static final Map<String, Integer> thunderCharge = Map.of("UNCOMMON", 0, "RARE", 150000, "EPIC", 1000000, "LEGENDARY", 5000000);
	public static final List<String> validRunes = List.of(
		"MUSIC_1",
		"MUSIC_2",
		"MUSIC_3",
		"ENCHANT_1",
		"ENCHANT_2",
		"ENCHANT_3",
		"GRAND_SEARING_3"
	);
	public static final String[] allowedRecombTypes = { "ACCESSORY", "NECKLACE", "GLOVES", "BRACELET", "BELT", "CLOAK" };
}

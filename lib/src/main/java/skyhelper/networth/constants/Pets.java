package skyhelper.networth.constants;

import com.google.gson.JsonObject;
import java.util.List;
import java.util.Map;

public class Pets {

	public static final List<String> blockedCandyReducePets = List.of("ENDER_DRAGON", "GOLDEN_DRAGON", "SCATHA");
	public static final List<String> recombPetItems = List.of("PET_ITEM_TIER_BOOST", "PET_ITEM_VAMPIRE_FANG", "PET_ITEM_TOY_JERRY");
	public static final List<String> soulboundPets = List.of("GRANDMA_WOLF", "KUUDRA", "BINGO");
	public static final List<String> tiers = List.of(
		"COMMON",
		"UNCOMMON",
		"RARE",
		"EPIC",
		"LEGENDARY",
		"MYTHIC",
		"DIVINE",
		"SPECIAL",
		"VERY_SPECIAL"
	);
	private static final Map<String, Integer> specialLevels = Map.of("GOLDEN_DRAGON", 200);
	private static final Map<String, Integer> rarityOffset = Map.of(
		"COMMON",
		0,
		"UNCOMMON",
		6,
		"RARE",
		11,
		"EPIC",
		16,
		"LEGENDARY",
		20,
		"MYTHIC",
		20
	);
	private static final List<Integer> levels = List.of(
		100,
		110,
		120,
		130,
		145,
		160,
		175,
		190,
		210,
		230,
		250,
		275,
		300,
		330,
		360,
		400,
		440,
		490,
		540,
		600,
		660,
		730,
		800,
		880,
		960,
		1050,
		1150,
		1260,
		1380,
		1510,
		1650,
		1800,
		1960,
		2130,
		2310,
		2500,
		2700,
		2920,
		3160,
		3420,
		3700,
		4000,
		4350,
		4750,
		5200,
		5700,
		6300,
		7000,
		7800,
		8700,
		9700,
		10800,
		12000,
		13300,
		14700,
		16200,
		17800,
		19500,
		21300,
		23200,
		25200,
		27400,
		29800,
		32400,
		35200,
		38200,
		41400,
		44800,
		48400,
		52200,
		56200,
		60400,
		64800,
		69400,
		74200,
		79200,
		84700,
		90700,
		97200,
		104200,
		111700,
		119700,
		128200,
		137200,
		146700,
		156700,
		167700,
		179700,
		192700,
		206700,
		221700,
		237700,
		254700,
		272700,
		291700,
		311700,
		333700,
		357700,
		383700,
		411700,
		441700,
		476700,
		516700,
		561700,
		611700,
		666700,
		726700,
		791700,
		861700,
		936700,
		1016700,
		1101700,
		1191700,
		1286700,
		1386700,
		1496700,
		1616700,
		1746700,
		1886700,
		0,
		1,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700,
		1886700
	);

	public static JsonObject getPetLevel(JsonObject pet) {
		String petType = pet.get("type").getAsString();
		String petTier = pet.get("tier").getAsString();
		long petExp = pet.get("exp").getAsLong();

		int maxPetLevel = specialLevels.getOrDefault(petType, 100);
		int petOffset = rarityOffset.get(petTier);
		List<Integer> petLevels = levels.subList(petOffset, petOffset + maxPetLevel - 1);

		int level = 1;
		long totalExp = 0;

		for (int i = 0; i < maxPetLevel; i++) {
			totalExp += petLevels.get(i);
			if (totalExp > petExp) {
				break;
			}

			level++;
		}

		pet.addProperty("level", Math.min(level, maxPetLevel));
		pet.addProperty("xpMax", petLevels.stream().mapToInt(i -> i).sum());
		return pet;
	}
}

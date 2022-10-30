package skyhelper.networth.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prestiges {

	public Map<String, List<String>> prestiges = new HashMap<>() {
		{
			put("HOT_CRIMSON_CHESTPLATE", List.of("CRIMSON_CHESTPLATE"));
			put("HOT_CRIMSON_HELMET", List.of("CRIMSON_HELMET"));
			put("HOT_CRIMSON_LEGGINGS", List.of("CRIMSON_LEGGINGS"));
			put("HOT_CRIMSON_BOOTS", List.of("CRIMSON_BOOTS"));
			put("BURNING_CRIMSON_CHESTPLATE", List.of("HOT_CRIMSON_CHESTPLATE", "CRIMSON_CHESTPLATE"));
			put("BURNING_CRIMSON_HELMET", List.of("HOT_CRIMSON_HELMET", "CRIMSON_HELMET"));
			put("BURNING_CRIMSON_LEGGINGS", List.of("HOT_CRIMSON_LEGGINGS", "CRIMSON_LEGGINGS"));
			put("BURNING_CRIMSON_BOOTS", List.of("HOT_CRIMSON_BOOTS", "CRIMSON_BOOTS"));
			put("FIERY_CRIMSON_CHESTPLATE", List.of("BURNING_CRIMSON_CHESTPLATE", "HOT_CRIMSON_CHESTPLATE", "CRIMSON_CHESTPLATE"));
			put("FIERY_CRIMSON_HELMET", List.of("BURNING_CRIMSON_HELMET", "HOT_CRIMSON_HELMET", "CRIMSON_HELMET"));
			put("FIERY_CRIMSON_LEGGINGS", List.of("BURNING_CRIMSON_LEGGINGS", "HOT_CRIMSON_LEGGINGS", "CRIMSON_LEGGINGS"));
			put("FIERY_CRIMSON_BOOTS", List.of("BURNING_CRIMSON_BOOTS", "HOT_CRIMSON_BOOTS", "CRIMSON_BOOTS"));
			put(
				"INFERNAL_CRIMSON_CHESTPLATE",
				List.of("FIERY_CRIMSON_CHESTPLATE", "BURNING_CRIMSON_CHESTPLATE", "HOT_CRIMSON_CHESTPLATE", "CRIMSON_CHESTPLATE")
			);
			put(
				"INFERNAL_CRIMSON_HELMET",
				List.of("FIERY_CRIMSON_HELMET", "BURNING_CRIMSON_HELMET", "HOT_CRIMSON_HELMET", "CRIMSON_HELMET")
			);
			put(
				"INFERNAL_CRIMSON_LEGGINGS",
				List.of("FIERY_CRIMSON_LEGGINGS", "BURNING_CRIMSON_LEGGINGS", "HOT_CRIMSON_LEGGINGS", "CRIMSON_LEGGINGS")
			);
			put("INFERNAL_CRIMSON_BOOTS", List.of("FIERY_CRIMSON_BOOTS", "BURNING_CRIMSON_BOOTS", "HOT_CRIMSON_BOOTS", "CRIMSON_BOOTS"));
			put("HOT_TERROR_CHESTPLATE", List.of("TERROR_CHESTPLATE"));
			put("HOT_TERROR_HELMET", List.of("TERROR_HELMET"));
			put("HOT_TERROR_LEGGINGS", List.of("TERROR_LEGGINGS"));
			put("HOT_TERROR_BOOTS", List.of("TERROR_BOOTS"));
			put("BURNING_TERROR_CHESTPLATE", List.of("HOT_TERROR_CHESTPLATE", "TERROR_CHESTPLATE"));
			put("BURNING_TERROR_HELMET", List.of("HOT_TERROR_HELMET", "TERROR_HELMET"));
			put("BURNING_TERROR_LEGGINGS", List.of("HOT_TERROR_LEGGINGS", "TERROR_LEGGINGS"));
			put("BURNING_TERROR_BOOTS", List.of("HOT_TERROR_BOOTS", "TERROR_BOOTS"));
			put("FIERY_TERROR_CHESTPLATE", List.of("BURNING_TERROR_CHESTPLATE", "HOT_TERROR_CHESTPLATE", "TERROR_CHESTPLATE"));
			put("FIERY_TERROR_HELMET", List.of("BURNING_TERROR_HELMET", "HOT_TERROR_HELMET", "TERROR_HELMET"));
			put("FIERY_TERROR_LEGGINGS", List.of("BURNING_TERROR_LEGGINGS", "HOT_TERROR_LEGGINGS", "TERROR_LEGGINGS"));
			put("FIERY_TERROR_BOOTS", List.of("BURNING_TERROR_BOOTS", "HOT_TERROR_BOOTS", "TERROR_BOOTS"));
			put(
				"INFERNAL_TERROR_CHESTPLATE",
				List.of("FIERY_TERROR_CHESTPLATE", "BURNING_TERROR_CHESTPLATE", "HOT_TERROR_CHESTPLATE", "TERROR_CHESTPLATE")
			);
			put("INFERNAL_TERROR_HELMET", List.of("FIERY_TERROR_HELMET", "BURNING_TERROR_HELMET", "HOT_TERROR_HELMET", "TERROR_HELMET"));
			put(
				"INFERNAL_TERROR_LEGGINGS",
				List.of("FIERY_TERROR_LEGGINGS", "BURNING_TERROR_LEGGINGS", "HOT_TERROR_LEGGINGS", "TERROR_LEGGINGS")
			);
			put("INFERNAL_TERROR_BOOTS", List.of("FIERY_TERROR_BOOTS", "BURNING_TERROR_BOOTS", "HOT_TERROR_BOOTS", "TERROR_BOOTS"));
			put("HOT_FERVOR_CHESTPLATE", List.of("FERVOR_CHESTPLATE"));
			put("HOT_FERVOR_HELMET", List.of("FERVOR_HELMET"));
			put("HOT_FERVOR_LEGGINGS", List.of("FERVOR_LEGGINGS"));
			put("HOT_FERVOR_BOOTS", List.of("FERVOR_BOOTS"));
			put("BURNING_FERVOR_CHESTPLATE", List.of("HOT_FERVOR_CHESTPLATE", "FERVOR_CHESTPLATE"));
			put("BURNING_FERVOR_HELMET", List.of("HOT_FERVOR_HELMET", "FERVOR_HELMET"));
			put("BURNING_FERVOR_LEGGINGS", List.of("HOT_FERVOR_LEGGINGS", "FERVOR_LEGGINGS"));
			put("BURNING_FERVOR_BOOTS", List.of("HOT_FERVOR_BOOTS", "FERVOR_BOOTS"));
			put("FIERY_FERVOR_CHESTPLATE", List.of("BURNING_FERVOR_CHESTPLATE", "HOT_FERVOR_CHESTPLATE", "FERVOR_CHESTPLATE"));
			put("FIERY_FERVOR_HELMET", List.of("BURNING_FERVOR_HELMET", "HOT_FERVOR_HELMET", "FERVOR_HELMET"));
			put("FIERY_FERVOR_LEGGINGS", List.of("BURNING_FERVOR_LEGGINGS", "HOT_FERVOR_LEGGINGS", "FERVOR_LEGGINGS"));
			put("FIERY_FERVOR_BOOTS", List.of("BURNING_FERVOR_BOOTS", "HOT_FERVOR_BOOTS", "FERVOR_BOOTS"));
			put(
				"INFERNAL_FERVOR_CHESTPLATE",
				List.of("FIERY_FERVOR_CHESTPLATE", "BURNING_FERVOR_CHESTPLATE", "HOT_FERVOR_CHESTPLATE", "FERVOR_CHESTPLATE")
			);
			put("INFERNAL_FERVOR_HELMET", List.of("FIERY_FERVOR_HELMET", "BURNING_FERVOR_HELMET", "HOT_FERVOR_HELMET", "FERVOR_HELMET"));
			put(
				"INFERNAL_FERVOR_LEGGINGS",
				List.of("FIERY_FERVOR_LEGGINGS", "BURNING_FERVOR_LEGGINGS", "HOT_FERVOR_LEGGINGS", "FERVOR_LEGGINGS")
			);
			put("INFERNAL_FERVOR_BOOTS", List.of("FIERY_FERVOR_BOOTS", "BURNING_FERVOR_BOOTS", "HOT_FERVOR_BOOTS", "FERVOR_BOOTS"));
			put("HOT_HOLLOW_CHESTPLATE", List.of("HOLLOW_CHESTPLATE"));
			put("HOT_HOLLOW_HELMET", List.of("HOLLOW_HELMET"));
			put("HOT_HOLLOW_LEGGINGS", List.of("HOLLOW_LEGGINGS"));
			put("HOT_HOLLOW_BOOTS", List.of("HOLLOW_BOOTS"));
			put("BURNING_HOLLOW_CHESTPLATE", List.of("HOT_HOLLOW_CHESTPLATE", "HOLLOW_CHESTPLATE"));
			put("BURNING_HOLLOW_HELMET", List.of("HOT_HOLLOW_HELMET", "HOLLOW_HELMET"));
			put("BURNING_HOLLOW_LEGGINGS", List.of("HOT_HOLLOW_LEGGINGS", "HOLLOW_LEGGINGS"));
			put("BURNING_HOLLOW_BOOTS", List.of("HOT_HOLLOW_BOOTS", "HOLLOW_BOOTS"));
			put("FIERY_HOLLOW_CHESTPLATE", List.of("BURNING_HOLLOW_CHESTPLATE", "HOT_HOLLOW_CHESTPLATE", "HOLLOW_CHESTPLATE"));
			put("FIERY_HOLLOW_HELMET", List.of("BURNING_HOLLOW_HELMET", "HOT_HOLLOW_HELMET", "HOLLOW_HELMET"));
			put("FIERY_HOLLOW_LEGGINGS", List.of("BURNING_HOLLOW_LEGGINGS", "HOT_HOLLOW_LEGGINGS", "HOLLOW_LEGGINGS"));
			put("FIERY_HOLLOW_BOOTS", List.of("BURNING_HOLLOW_BOOTS", "HOT_HOLLOW_BOOTS", "HOLLOW_BOOTS"));
			put(
				"INFERNAL_HOLLOW_CHESTPLATE",
				List.of("FIERY_HOLLOW_CHESTPLATE", "BURNING_HOLLOW_CHESTPLATE", "HOT_HOLLOW_CHESTPLATE", "HOLLOW_CHESTPLATE")
			);
			put("INFERNAL_HOLLOW_HELMET", List.of("FIERY_HOLLOW_HELMET", "BURNING_HOLLOW_HELMET", "HOT_HOLLOW_HELMET", "HOLLOW_HELMET"));
			put(
				"INFERNAL_HOLLOW_LEGGINGS",
				List.of("FIERY_HOLLOW_LEGGINGS", "BURNING_HOLLOW_LEGGINGS", "HOT_HOLLOW_LEGGINGS", "HOLLOW_LEGGINGS")
			);
			put("INFERNAL_HOLLOW_BOOTS", List.of("FIERY_HOLLOW_BOOTS", "BURNING_HOLLOW_BOOTS", "HOT_HOLLOW_BOOTS", "HOLLOW_BOOTS"));
			put("HOT_AURORA_CHESTPLATE", List.of("AURORA_CHESTPLATE"));
			put("HOT_AURORA_HELMET", List.of("AURORA_HELMET"));
			put("HOT_AURORA_LEGGINGS", List.of("AURORA_LEGGINGS"));
			put("HOT_AURORA_BOOTS", List.of("AURORA_BOOTS"));
			put("BURNING_AURORA_CHESTPLATE", List.of("HOT_AURORA_CHESTPLATE", "AURORA_CHESTPLATE"));
			put("BURNING_AURORA_HELMET", List.of("HOT_AURORA_HELMET", "AURORA_HELMET"));
			put("BURNING_AURORA_LEGGINGS", List.of("HOT_AURORA_LEGGINGS", "AURORA_LEGGINGS"));
			put("BURNING_AURORA_BOOTS", List.of("HOT_AURORA_BOOTS", "AURORA_BOOTS"));
			put("FIERY_AURORA_CHESTPLATE", List.of("BURNING_AURORA_CHESTPLATE", "HOT_AURORA_CHESTPLATE", "AURORA_CHESTPLATE"));
			put("FIERY_AURORA_HELMET", List.of("BURNING_AURORA_HELMET", "HOT_AURORA_HELMET", "AURORA_HELMET"));
			put("FIERY_AURORA_LEGGINGS", List.of("BURNING_AURORA_LEGGINGS", "HOT_AURORA_LEGGINGS", "AURORA_LEGGINGS"));
			put("FIERY_AURORA_BOOTS", List.of("BURNING_AURORA_BOOTS", "HOT_AURORA_BOOTS", "AURORA_BOOTS"));
			put(
				"INFERNAL_AURORA_CHESTPLATE",
				List.of("FIERY_AURORA_CHESTPLATE", "BURNING_AURORA_CHESTPLATE", "HOT_AURORA_CHESTPLATE", "AURORA_CHESTPLATE")
			);
			put("INFERNAL_AURORA_HELMET", List.of("FIERY_AURORA_HELMET", "BURNING_AURORA_HELMET", "HOT_AURORA_HELMET", "AURORA_HELMET"));
			put(
				"INFERNAL_AURORA_LEGGINGS",
				List.of("FIERY_AURORA_LEGGINGS", "BURNING_AURORA_LEGGINGS", "HOT_AURORA_LEGGINGS", "AURORA_LEGGINGS")
			);
			put("INFERNAL_AURORA_BOOTS", List.of("FIERY_AURORA_BOOTS", "BURNING_AURORA_BOOTS", "HOT_AURORA_BOOTS", "AURORA_BOOTS"));
		}
	};
}
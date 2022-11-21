package skyhelper.networth.helper;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.calculator.ItemCalculator;
import skyhelper.networth.calculator.PetCalculator;
import skyhelper.networth.calculator.SacksCalculator;
import skyhelper.networth.constants.Pets;

public record NetworthData(
	boolean noInventory,
	double networth,
	double unsoulboundNetworth,
	double purse,
	double bank,
	Map<String, CategoryData> types
) {
	public static NetworthData calculateNetworth(
		NetworthItems items,
		double purseBalance,
		double bankBalance,
		Map<String, Double> prices,
		boolean onlyNetworth
	) throws NetworthException {
		Map<String, CategoryData> categories = new HashMap<>();

		for (Map.Entry<String, List<Object>> category : items.getItems().entrySet()) {
			double total = 0;
			double unsoulboundTotal = 0;
			List<BaseItemData> calculatedItems = new ArrayList<>();

			for (Object item : category.getValue()) {
				BaseItemData result =
					switch (category.getKey()) {
						case "pets" -> PetCalculator.calculatePet((JsonObject) item, prices);
						case "sacks" -> SacksCalculator.calculateSackItem((JsonObject) item, prices);
						default -> ItemCalculator.calculateItem((NBTCompound) item, prices);
					};

				if (result != null) {
					total += result.getPrice();
					if (!result.isSoulbound()) {
						unsoulboundTotal += result.getPrice();
					}
					if (!onlyNetworth) {
						calculatedItems.add(result);
					}
				}
			}

			// Sort items by price
			if (!onlyNetworth) {
				if (!calculatedItems.isEmpty()) {
					calculatedItems.sort(Comparator.comparingDouble(BaseItemData::getPrice));
				}
			} else {
				calculatedItems = null;
			}

			categories.put(category.getKey(), new CategoryData(total, unsoulboundTotal, calculatedItems));
		}

		// Calculate total networth
		double total = categories.values().stream().mapToDouble(CategoryData::total).sum() + bankBalance + purseBalance;
		double unsoulboundTotal =
			categories.values().stream().mapToDouble(CategoryData::unsoulboundTotal).sum() + bankBalance + purseBalance;

		return new NetworthData(
			items.getItems().get("inventory").isEmpty(),
			total,
			unsoulboundTotal,
			purseBalance,
			bankBalance,
			categories
		);
	}

	public static BaseItemData calculateItemNetworth(Object item, Map<String, Double> prices) throws NetworthException {
		boolean isPet = item instanceof JsonObject || ((NBTCompound) item).containsKey("tag.ExtraAttributes.petInfo");
		if (isPet) {
			JsonObject petInfo = item instanceof JsonObject petObj
				? petObj
				: JsonParser.parseString(((NBTCompound) item).getString("tag.ExtraAttributes.petInfo")).getAsJsonObject();
			return PetCalculator.calculatePet(Pets.getPetLevel(petInfo), prices);
		}
		return ItemCalculator.calculateItem((NBTCompound) item, prices);
	}

	public record CategoryData(double total, double unsoulboundTotal, List<BaseItemData> items) {}

	@AllArgsConstructor
	@Getter
	public static class BaseItemData {

		private final String name;
		private final double price;
		private final List<CalculationData> calculation;
		private final boolean soulbound;
	}

	@Getter
	public static class ItemData extends BaseItemData {

		private final String id;
		private final int count;
		private final double base;

		public ItemData(
			String name,
			String id,
			double price,
			double base,
			List<CalculationData> calculation,
			int count,
			boolean soulbound
		) {
			super(name, price, calculation, soulbound);
			this.id = id;
			this.count = count;
			this.base = base;
		}
	}

	@Getter
	public static class SacksItemData extends BaseItemData {

		private final String id;
		private final int count;

		public SacksItemData(String name, String id, double price, List<CalculationData> calculation, int count, boolean soulbound) {
			super(name, price, calculation, soulbound);
			this.id = id;
			this.count = count;
		}
	}

	@Getter
	public static class PetItemData extends BaseItemData {

		private final String type;
		private final int level;
		private final long xpMax;
		private final double base;
		private final String tier;

		public PetItemData(
			String name,
			double price,
			List<CalculationData> calculation,
			boolean soulbound,
			String type,
			int level,
			long xpMax,
			double base,
			String tier
		) {
			super(name, price, calculation, soulbound);
			this.type = type;
			this.level = level;
			this.xpMax = xpMax;
			this.base = base;
			this.tier = tier;
		}
	}

	public record CalculationData(String id, String type, double price, int count) {}
}

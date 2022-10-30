package skyhelper.networth.helper;

import com.google.gson.JsonObject;
import java.util.*;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.calculator.ItemCalculator;
import skyhelper.networth.calculator.PetCalculator;
import skyhelper.networth.calculator.SacksCalculator;

public record Networth(
	boolean noInventory,
	double networth,
	double unsoulboundNetworth,
	double purse,
	double bank,
	Map<String, CategoryData> types
) {
	public record CategoryData(double total, double unsoulboundTotal, List<ItemData> items) {}

	public record ItemData(
		String name,
		String id,
		double price,
		double base,
		List<CalculationData> calculation,
		int count,
		boolean soulbound
	) {}

	public record CalculationData(String id, String type, double price, int count) {}

	public static Networth calculateNetworth(
		NetworthItems items,
		double purseBalance,
		double bankBalance,
		Map<String, Double> prices,
		boolean onlyNetworth
	) {
		Map<String, CategoryData> categories = new HashMap<>();

		for (Map.Entry<String, List<Object>> category : items.getItems().entrySet()) {
			double total = 0;
			double unsoulboundTotal = 0;
			List<ItemData> calculatedItems = new ArrayList<>();

			for (Object item : category.getValue()) {
				ItemData result =
					switch (category.getKey()) {
						case "pets" -> PetCalculator.calculatePet((JsonObject) item, prices);
						case "sacks" -> SacksCalculator.calculateSackItem((JsonObject) item, prices);
						default -> ItemCalculator.calculateItem((NBTCompound) item, prices);
					};

				if (result != null) {
					total += result.price();
					if (!result.soulbound()) {
						unsoulboundTotal += result.price();
					}
					if (!onlyNetworth) {
						calculatedItems.add(result);
					}
				}
			}

			// Sort items by price
			if (!onlyNetworth) {
				if (!calculatedItems.isEmpty()) {
					calculatedItems.sort(Comparator.comparingDouble(ItemData::price));
				}
			} else {
				calculatedItems = null;
			}

			categories.put(category.getKey(), new CategoryData(total, unsoulboundTotal, calculatedItems));
		}

		// Calculate total networth
		double total = categories.values().stream().mapToDouble(CategoryData::total).sum() + bankBalance + purseBalance;
		double unsoulboundTotal = categories.values().stream().mapToDouble(CategoryData::unsoulboundTotal).sum() + bankBalance + purseBalance;

		return new Networth(items.getItems().get("inventory").isEmpty(), total, unsoulboundTotal, purseBalance, bankBalance, categories);
	}
}

package skyhelper.networth.calculator;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import skyhelper.networth.SkyHelperNetworth;
import skyhelper.networth.constants.Misc;
import skyhelper.networth.helper.NetworthData;

public class SacksCalculator {

	public static NetworthData.SacksItemData calculateSackItem(JsonObject item, SkyHelperNetworth skyHelperNetworth) {
		String id = item.get("id").getAsString();
		int amount = item.get("amount").getAsInt();

		double itemPrice = skyHelperNetworth.getPrices().getOrDefault(id, 0.0);
		if (id.startsWith("RUNE_") && !Misc.validRunes.contains(id)) {
			return null;
		}
		if (itemPrice > 0) {
			return new NetworthData.SacksItemData(
				skyHelperNetworth
					.getSkyblockItems()
					.entrySet()
					.stream()
					.map(s -> s.getValue().getAsJsonObject())
					.filter(s -> s.get("id").getAsString().equals(id))
					.map(s -> s.get("name").getAsString())
					.findAny()
					.orElse("Unknown"),
				id,
				itemPrice * amount,
				new ArrayList<>(),
				amount,
				false
			);
		} else {
			return null;
		}
	}
}

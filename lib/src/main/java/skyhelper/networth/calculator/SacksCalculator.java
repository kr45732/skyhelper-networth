package skyhelper.networth.calculator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import skyhelper.networth.SkyHelperNetworth;
import skyhelper.networth.constants.Misc;
import skyhelper.networth.helper.NetworthData;

public class SacksCalculator {

	public static NetworthData.SacksItemData calculateSackItem(JsonObject item, SkyHelperNetworth skyHelperNetworth) {
		String id = item.get("id").getAsString();
		int amount = item.get("amount").getAsInt();

		double itemPrice = skyHelperNetworth.prices.getOrDefault(id, 0.0);
		if (id.startsWith("RUNE_") && !Misc.validRunes.contains(id)) {
			return null;
		}
		if (itemPrice > 0) {
			return new NetworthData.SacksItemData(
				skyHelperNetworth.skyblockItems
					.asList()
					.stream()
					.map(JsonElement::getAsJsonObject)
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

package skyhelper.networth.calculator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.SkyHelperNetworth;
import skyhelper.networth.constants.Pets;
import skyhelper.networth.helper.Functions;
import skyhelper.networth.helper.NetworthData;

public class ItemCalculator {

	public static NetworthData.BaseItemData calculateItem(NBTCompound item, SkyHelperNetworth skyHelperNetworth) {
		if (item.getString("tag.ExtraAttributes.id").equals("PET") && item.containsKey("tag.ExtraAttributes.petInfo")) {
			JsonObject petInfo = JsonParser.parseString(item.getString("tag.ExtraAttributes.petInfo")).getAsJsonObject();
			return PetCalculator.calculatePet(Pets.getPetLevel(petInfo), skyHelperNetworth);
		}

		if (item.containsKey("tag.ExtraAttributes.id")) {
			String itemName = item.getString("tag.display.Name").replaceAll("§[\\da-fk-or]", "");
			String itemId = item.getString("tag.ExtraAttributes.id").toLowerCase();
			NBTCompound extraAttributes = item.getCompound("tag.ExtraAttributes");
			String finalItemId = itemId; // Lambdas ;-;
			JsonObject skyblockItem = skyHelperNetworth.skyblockItems
				.asList()
				.stream()
				.map(JsonElement::getAsJsonObject)
				.filter(o -> o.get("id").getAsString().equalsIgnoreCase(finalItemId))
				.findAny()
				.orElse(null);

			if (skyblockItem == null) { // Shouldn't happen
				return null;
			}

			if (extraAttributes.containsKey("skin")) {
				itemId += "_skinned_" + extraAttributes.getString("skin").toLowerCase();
			}

			if (List.of("Beastmaster Crest", "Griffin Upgrade Stone", "Wisp Upgrade Stone").contains(itemName)) {
				itemName =
					itemName +
					" " +
					(skyblockItem.has("tier") ? Functions.titleCase(skyblockItem.get("tier").getAsString().replace("_", " ")) : "Unknown");
			} else if (itemName.endsWith(" Exp Boost")) {
				String[] idSplit = skyblockItem.get("id").getAsString().split("_");
				itemName = itemName + " " + (skyblockItem.has("id") ? Functions.titleCase(idSplit[idSplit.length - 1]) : "Unknown");
			}

			// RUNES (Item)
			if (
				extraAttributes.getString("id").equals("RUNE") &&
				extraAttributes.containsKey("runes") &&
				!extraAttributes.getCompound("runes").isEmpty()
			) {
				Map.Entry<String, Object> runeEntry = extraAttributes.getCompound("runes").entrySet().iterator().next();
				itemId = ("rune_" + runeEntry.getKey() + "_" + runeEntry.getValue()).toLowerCase();
			}
			// CAKES (Item)
			if (extraAttributes.getString("id").equals("NEW_YEAR_CAKE")) {
				itemId = "new_year_cake_" + extraAttributes.get("new_years_cake");
			}
			// PARTY_HAT_CRAB (Item)
			if (extraAttributes.getString("id").startsWith("PARTY_HAT_CRAB") && extraAttributes.containsKey("party_hat_color")) {
				itemId = extraAttributes.getString("id").toLowerCase() + "_" + extraAttributes.getString("party_hat_color");
			}

			Double itemData = skyHelperNetworth.prices.get(itemId);
			double price = (itemData != null ? itemData : 0) * item.getInt("Count", 1);
			double base = (itemData != null ? itemData : 0) * item.getInt("Count", 1);
			if (price == 0 && extraAttributes.containsKey("price")) {
				price = base = Integer.parseInt(extraAttributes.getString("price")) * 0.85;
			}
			List<NetworthData.CalculationData> calculation = new ArrayList<>();

			// TODO: all the calculations

			boolean isSoulbound =
				extraAttributes.containsKey("donated_museum") ||
				item.getList("tag.display.Lore").contains("§8§l* §8Co-op Soulbound §8§l*") ||
				item.getList("tag.display.Lore").contains("§8§l* §8Soulbound §8§l*");
			return new NetworthData.ItemData(itemName, itemId, price, base, calculation, item.getInt("Count", 1), isSoulbound);
		}

		return null;
	}
}

package skyhelper.networth;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.helper.NetworthData;
import skyhelper.networth.helper.NetworthException;
import skyhelper.networth.helper.NetworthItems;

public class SkyHelperNetworth {

	private static JsonArray skyblockItems;

	public static JsonArray getSkyblockItems() throws NetworthException {
		if (skyblockItems == null) {
			try (FileReader reader = new FileReader("src/main/resources/items.json")) {
				skyblockItems = JsonParser.parseReader(reader).getAsJsonArray();
			} catch (Exception e) {
				throw new NetworthException("Unable to locate or parse items.json", e);
			}
		}
		return skyblockItems;
	}

	public NetworthData getNetworth(JsonObject profileData, double bankBalance, boolean onlyNetworth, Map<String, Double> prices)
		throws NetworthException {
		double purse = profileData.has("coin_purse") ? profileData.get("coin_purse").getAsDouble() : 0;
		prices = parsePrices(prices);
		NetworthItems items = new NetworthItems(profileData);
		return NetworthData.calculateNetworth(items, purse, bankBalance, prices, onlyNetworth);
	}

	public NetworthData.BaseItemData getItemNetworth(Object item, Map<String, Double> prices) throws NetworthException {
		if (!((item instanceof NBTCompound nbt && nbt.containsKey("tag")) || (item instanceof JsonObject petJson && petJson.has("exp")))) {
			throw new NetworthException("Invalid item provided");
		}
		prices = parsePrices(prices);
		return NetworthData.calculateItemNetworth(item, prices);
	}

	private Map<String, Double> parsePrices(Map<String, Double> prices) throws NetworthException {
		try {
			if (prices != null) {
				if (prices.keySet().stream().anyMatch(price -> !price.equals(price.toLowerCase()))) {
					prices = prices.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toLowerCase(), Map.Entry::getValue));
				}
			} else {
				prices = getPrices();
			}
			return prices;
		} catch (Exception e) {
			throw new NetworthException("Unable to parse prices", e);
		}
	}

	private Map<String, Double> getPrices() throws NetworthException {
		try (
			InputStream in = URI
				.create("https://raw.githubusercontent.com/SkyHelperBot/Prices/main/prices.json")
				.toURL()
				.openConnection()
				.getInputStream();
			InputStreamReader reader = new InputStreamReader(in)
		) {
			return JsonParser
				.parseReader(reader)
				.getAsJsonObject()
				.entrySet()
				.stream()
				.collect(Collectors.toMap(e -> e.getKey().toLowerCase(), e -> e.getValue().getAsDouble()));
		} catch (Exception e) {
			throw new NetworthException("Failed to retrieve prices", e);
		}
	}
}

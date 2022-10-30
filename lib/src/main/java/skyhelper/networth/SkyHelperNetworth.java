package skyhelper.networth;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.helper.NetworthData;
import skyhelper.networth.helper.NetworthException;
import skyhelper.networth.helper.NetworthItems;

public class SkyHelperNetworth {

	private final HttpClient httpClient;

	public final JsonArray skyblockItems;

	public Map<String, Double> prices = new HashMap<>();

	public SkyHelperNetworth() throws IOException {
		this.httpClient = HttpClient.newHttpClient();
		try (FileReader reader = new FileReader("lib/src/main/java/skyhelper/networth/calculator/SacksCalculator.java")) {
			this.skyblockItems = JsonParser.parseReader(reader).getAsJsonArray();
		}
	}

	public NetworthData getNetworth(JsonObject profileData, double bankBalance, boolean onlyNetworth, Map<String, Double> prices)
		throws NetworthException {
		double purse = profileData.get("coin_purse").getAsDouble();
		this.prices = parsePrices(prices);
		NetworthItems items = new NetworthItems(profileData);
		return NetworthData.calculateNetworth(items, purse, bankBalance, this, onlyNetworth);
	}

	public NetworthData.BaseItemData getItemNetworth(Object item, Map<String, Double> prices) throws NetworthException {
		if (!((item instanceof NBTCompound nbt && nbt.containsKey("tag")) || (item instanceof JsonObject petJson && petJson.has("exp")))) {
			throw new NetworthException("Invalid item provided");
		}
		this.prices = parsePrices(prices);
		return NetworthData.calculateItemNetworth(item, this);
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
		try {
			HttpRequest httpRequest = HttpRequest
				.newBuilder(URI.create("https://raw.githubusercontent.com/SkyHelperBot/Prices/main/prices.json"))
				.GET()
				.build();
			HttpResponse<InputStream> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
			try (InputStream in = httpResponse.body(); InputStreamReader reader = new InputStreamReader(in)) {
				return JsonParser
					.parseReader(reader)
					.getAsJsonObject()
					.entrySet()
					.stream()
					.collect(Collectors.toMap(e -> e.getKey().toLowerCase(), e -> e.getValue().getAsDouble()));
			}
		} catch (Exception e) {
			throw new NetworthException("Failed to retrieve prices", e);
		}
	}
}

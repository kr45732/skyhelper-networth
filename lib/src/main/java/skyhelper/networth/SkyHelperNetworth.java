package skyhelper.networth;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import skyhelper.networth.helper.NetworthData;
import skyhelper.networth.helper.NetworthException;
import skyhelper.networth.helper.NetworthItems;

public class SkyHelperNetworth {

	private final CloseableHttpClient httpClient;

	@Getter
	private final JsonObject skyblockItems;

	@Getter
	private Map<String, Double> prices = new HashMap<>();

	public SkyHelperNetworth() throws IOException {
		this.httpClient = HttpClientBuilder.create().build();
		try (FileReader reader = new FileReader("lib/src/main/java/skyhelper/networth/calculator/SacksCalculator.java")) {
			this.skyblockItems = JsonParser.parseReader(reader).getAsJsonObject();
		}
	}

	public NetworthData getNetworth(JsonObject profileData, double bankBalance, boolean onlyNetworth, Map<String, Double> prices)
		throws NetworthException {
		double purse = profileData.get("coin_purse").getAsDouble();
		this.prices = parsePrices(prices);
		NetworthItems items = new NetworthItems(profileData);
		return NetworthData.calculateNetworth(items, purse, bankBalance, this, onlyNetworth);
	}

	private Map<String, Double> parsePrices(Map<String, Double> prices) throws NetworthException {
		try {
			if (prices != null) {
				if (prices.keySet().stream().anyMatch(price -> !price.equals(price.toLowerCase()))) {
					prices = prices.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toLowerCase(), Map.Entry::getValue));
				}
			} else {
				prices = loadPrices();
			}
			return prices;
		} catch (Exception e) {
			throw new NetworthException("Unable to parse prices", e);
		}
	}

	private Map<String, Double> loadPrices() throws NetworthException {
		try {
			HttpGet httpGet = new HttpGet("https://raw.githubusercontent.com/SkyHelperBot/Prices/main/prices.json");
			httpGet.addHeader("content-type", "application/json; charset=UTF-8");

			try (
				CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
				InputStreamReader in = new InputStreamReader(httpResponse.getEntity().getContent());
				JsonReader jsonIn = new JsonReader(in)
			) {
				return JsonParser
					.parseReader(jsonIn)
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

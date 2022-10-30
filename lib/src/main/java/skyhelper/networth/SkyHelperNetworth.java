package skyhelper.networth;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import skyhelper.networth.helper.NetworthData;
import skyhelper.networth.helper.NetworthException;
import skyhelper.networth.helper.NetworthItems;
import skyhelper.networth.helper.ProfileData;

public class SkyHelperNetworth {

	private final CloseableHttpClient httpClient;

	public SkyHelperNetworth() {
		this.httpClient = HttpClientBuilder.create().build();
	}

	public NetworthData getNetworth(ProfileData profileData, double bankBalance, boolean onlyNetworth, Map<String, Double> prices)
		throws NetworthException {
		double purse = profileData.getElement("coin_purse").getAsDouble();
		prices = parsePrices(prices);
		NetworthItems items = new NetworthItems(profileData);
		return NetworthData.calculateNetworth(items, purse, bankBalance, prices, onlyNetworth);
	}

	public Map<String, Double> parsePrices(Map<String, Double> prices) throws NetworthException {
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

	public Map<String, Double> getPrices() throws NetworthException {
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

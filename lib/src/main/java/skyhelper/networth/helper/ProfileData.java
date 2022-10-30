package skyhelper.networth.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public record ProfileData(JsonObject profileData) {
	public JsonElement getElement(String path) {
		return getElement(profileData, path);
	}

	public boolean hasElement(String path) {
		return getElement(path) != null;
	}
	public JsonElement getElement(JsonElement element, String path) {
		String[] paths = path.split("\\.");

		try {
			for (String key : paths) {
				if (key.length() >= 3 && key.startsWith("[") && key.endsWith("]")) {
					int idx = Integer.parseInt(key.substring(1, key.length() - 1));
					element = element.getAsJsonArray().get(idx == -1 ? element.getAsJsonArray().size() - 1 : idx);
				} else {
					element = element.getAsJsonObject().get(key);
				}
			}
			return element;
		} catch (Exception e) {
			return null;
		}
	}
}

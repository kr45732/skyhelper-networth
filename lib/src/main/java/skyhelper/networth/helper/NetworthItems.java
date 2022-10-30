package skyhelper.networth.helper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.nullicorn.nedit.NBTReader;
import me.nullicorn.nedit.type.NBTCompound;
import me.nullicorn.nedit.type.NBTList;
import me.nullicorn.nedit.type.TagType;
import skyhelper.networth.constants.Pets;

public class NetworthItems {

	private static final Map<String, String> singleContainers = Map.of(
		"armor",
		"inv_armor",
		"equippment",
		"equippment_contents",
		"wardrobe",
		"wardrobe_contents",
		"inventory",
		"inv_contents",
		"enderchest",
		"ender_chest_contents",
		"accessories",
		"talisman_bag",
		"personal_vault",
		"personal_vault_contents"
	);

	private final Map<String, List<Object>> items = new HashMap<>();

	public NetworthItems(ProfileData profileData) throws NetworthException {
		// Parse Sacks
		items.put("sacks", new ArrayList<>());
		if (profileData.hasElement("sacks_counts")) {
			items
				.get("sacks")
				.addAll(
					profileData
						.getElement("sacks_counts")
						.getAsJsonObject()
						.entrySet()
						.stream()
						.map(e -> {
							JsonObject sackObj = new JsonObject();
							sackObj.addProperty("id", e.getKey());
							sackObj.add("amount", e.getValue());
							return sackObj;
						})
						.toList()
				);
		}

		for (Map.Entry<String, String> category : singleContainers.entrySet()) {
			items.put(category.getKey(), new ArrayList<>());
			if (profileData.hasElement(category.getValue())) {
				items.get(category.getKey()).addAll(parseContainer(profileData.getElement(category.getValue()).getAsString()));
			}
		}

		// Parse Storage
		items.put("storage", new ArrayList<>());
		if (profileData.hasElement("backpack_contents") && profileData.hasElement("backpack_icons")) {
			// Parse Storage Contents
			for (JsonElement backpackContent : profileData.getElement("backpack_contents").getAsJsonArray()) {
				items.get("storage").addAll(parseContainer(backpackContent.getAsJsonObject().get("data").getAsString()));
			}

			// Parse Storage Backpacks
			for (JsonElement backpack : profileData.getElement("backpack_icons").getAsJsonArray()) {
				items.get("storage").addAll(parseContainer(backpack.getAsJsonObject().get("data").getAsString()));
			}
		}

		// Parse pets
		items.put("pets", new ArrayList<>());
		if (profileData.hasElement("pets")) {
			items
				.get("pets")
				.addAll(
					profileData
						.getElement("pets")
						.getAsJsonArray()
						.asList()
						.stream()
						.map(p -> Pets.getPetLevel(p.getAsJsonObject()))
						.toList()
				);
		}
	}

	private List<NBTCompound> parseContainer(String data) throws NetworthException {
		try {
			List<NBTCompound> parsed = new ArrayList<>();

			NBTList decoded = NBTReader.readBase64(data).getList("i");
			for (int i = 0; i < decoded.size(); i++) {
				NBTCompound item = decoded.getCompound(i);

				if (item.containsKey("tag.ExtraAttributes.new_year_cake_bag_data")) {
					try (
						ByteArrayInputStream backpackStream = new ByteArrayInputStream(
							item.getByteArray("tag.ExtraAttributes.new_year_cake_bag_data")
						)
					) {
						NBTList parsedCakes = new NBTList(TagType.INT);

						NBTList cakes = NBTReader.read(backpackStream).getList("i");
						for (int j = 0; j < cakes.size(); j++) {
							int cakeNumber = cakes.getCompound(j).getInt("tag.ExtraAttributes.new_years_cake", -1);
							if (cakeNumber != -1) {
								parsedCakes.add(cakeNumber);
							}
						}

						item.getCompound("tag.ExtraAttributes").put("new_year_cake_bag_years", parsedCakes);
					}
				}

				parsed.add(item);
			}

			return parsed;
		} catch (Exception e) {
			throw new NetworthException("Error parsing NBT data", e);
		}
	}

	public Map<String, List<Object>> getItems() {
		return items;
	}
}

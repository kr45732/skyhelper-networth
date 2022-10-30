package skyhelper.networth.calculator;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.nullicorn.nedit.type.NBTCompound;
import skyhelper.networth.SkyHelperNetworth;
import skyhelper.networth.constants.Pets;
import skyhelper.networth.helper.NetworthData;

public class ItemCalculator {

	public static NetworthData.BaseItemData calculateItem(NBTCompound item, SkyHelperNetworth skyHelperNetworth) {
		if (item.getString("tag.ExtraAttributes.id").equals("PET") && item.containsKey("tag.ExtraAttributes.petInfo")) {
			JsonObject petInfo = JsonParser.parseString(item.getString("tag.ExtraAttributes.petInfo")).getAsJsonObject();
			return PetCalculator.calculatePet(Pets.getPetLevel(petInfo), skyHelperNetworth);
		}

		if (item.containsKey("tag.ExtraAttributes.id")) {
			// TODO
		}

		return null;
	}
}

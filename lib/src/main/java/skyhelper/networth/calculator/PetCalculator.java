package skyhelper.networth.calculator;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import skyhelper.networth.SkyHelperNetworth;
import skyhelper.networth.constants.ApplicationWorth;
import skyhelper.networth.constants.Pets;
import skyhelper.networth.helper.Functions;
import skyhelper.networth.helper.NetworthData;

public class PetCalculator {

	public static NetworthData.PetItemData calculatePet(JsonObject pet, SkyHelperNetworth skyHelperNetworth) {
		String petTier = pet.get("tier").getAsString();
		String petType = pet.get("type").getAsString();
		String petSkin = pet.has("skin") ? pet.get("skin").getAsString() : null;
		int petLevel = pet.get("level").getAsInt();

		String tierName = (petTier + "_" + petType).toLowerCase();
		double lvl1 = skyHelperNetworth.prices.getOrDefault(
			("lvl_1_" + tierName + (petSkin != null ? "_skinned_$" + petSkin : "")).toLowerCase(),
			skyHelperNetworth.prices.getOrDefault("lvl_1_" + tierName, 0.0)
		);
		double lvl100 = skyHelperNetworth.prices.getOrDefault(
			("lvl_100_" + tierName + (petSkin != null ? "_skinned_$" + petSkin : "")).toLowerCase(),
			skyHelperNetworth.prices.getOrDefault("lvl_100_" + tierName, 0.0)
		);
		double lvl200 = skyHelperNetworth.prices.getOrDefault(
			("lvl_200_" + tierName + (petSkin != null ? "_skinned_$" + petSkin : "")).toLowerCase(),
			skyHelperNetworth.prices.getOrDefault("lvl_200_" + tierName, 0.0)
		);

		String petName = "[Lvl " + petLevel + "] " + Functions.titleCase(petTier + " " + petType) + (petSkin != null ? " ✦" : "");
		if (lvl1 == 0 || lvl100 == 0) {
			return null;
		}

		double price = lvl200 != 0 ? lvl200 : lvl100;
		List<NetworthData.CalculationData> calculation = new ArrayList<>();

		long petXpMax = pet.get("xpMax").getAsLong();
		long petExp = pet.get("exp").getAsLong();

		// BASE
		if (petLevel < 100 && petXpMax != 0) {
			double baseFormula = (lvl100 - lvl1) / petXpMax;

			if (baseFormula != 0) {
				price = baseFormula * petExp + lvl1;
			}
		}

		if (petLevel > 100 && petLevel < 200) {
			int level = petLevel - 100;

			if (level != 1) {
				double baseFormula = (lvl200 - lvl100) / 100;

				if (baseFormula != 0) {
					price = baseFormula * level + lvl100;
				}
			}
		}

		double base = price;

		// PET ITEM
		String heldItem = pet.has("heldItem") ? pet.get("heldItem").getAsString() : null;
		if (heldItem != null) {
			NetworthData.CalculationData calculationData = new NetworthData.CalculationData(
				heldItem,
				"pet_item",
				skyHelperNetworth.prices.getOrDefault(heldItem.toLowerCase(), 0.0) * ApplicationWorth.applicationWorth.get("petItem"),
				1
			);
			price += calculationData.price();
			calculation.add(calculationData);
		}

		// PET CANDY REDUCE
		if (pet.get("candyUsed").getAsInt() > 0 && !Pets.blockedCandyReducePets.contains(petType)) {
			double reducedValue = price * ApplicationWorth.applicationWorth.get("petCandy");

			if (!Double.isNaN(price)) {
				if (petLevel == 100) {
					price = Math.max(reducedValue, price - 5000000);
				} else {
					price = Math.max(reducedValue, price - 2500000);
				}
			}
		}

		petTier = Pets.recombPetItems.contains(heldItem) ? Pets.tiers.get(Pets.tiers.indexOf(petTier) + 1) : petTier;

		return new NetworthData.PetItemData(
			petName,
			price,
			calculation,
			Pets.soulboundPets.contains(petType),
			petType,
			petLevel,
			petXpMax,
			base,
			petTier
		);
	}
}

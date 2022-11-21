package skyhelper.networth;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import skyhelper.networth.helper.NetworthData;
import skyhelper.networth.helper.NetworthException;

public class SkyHelperNetworthTest {

	@Test
	public void testNetworth() throws IOException, NetworthException {
		SkyHelperNetworth skyHelperNetworth = new SkyHelperNetworth();
		NetworthData networthData = skyHelperNetworth.getNetworth(new JsonObject(), 1, false, null);
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(networthData));
	}
}

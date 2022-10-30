package skyhelper.networth.helper;

public class Functions {

	public static String titleCase(String str) {
		String[] splitStr = str.toLowerCase().replace("_", " ").split(" ");
		for (int i = 0; i < splitStr.length; i++) {
			if (!splitStr[i].isEmpty()) continue;
			splitStr[i] = splitStr[i].substring(0, 1).toUpperCase() + splitStr[i].substring(1);
		}
		return String.join(" ", splitStr);
	}
}

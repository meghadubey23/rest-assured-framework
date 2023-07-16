package apis.libraryApi;

public class DynamicPayload {

    public static String addBookPayload(String isbn, String aisle) {
        String pl = "{\n" +
                "\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\"" + isbn + "\",\n" +
                "\"aisle\":\"" + aisle + "\",\n" +
                "\"author\":\"Megha Dubey\"\n" +
                "}\n";
        return pl;
    }

    public static String deleteBookPayload(String id) {
        String pl = "{\n" +
                " \"ID\" : \"" + id + "\"\n" +
                "} ";
        return pl;
    }

}

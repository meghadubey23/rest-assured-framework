package apis.googleMapsAPI;

public class RequestBody {

    public static String postRequestBody() {
        String request = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house123\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
        return request;
    }

    public static String putRequestBody(String place_id) {
        String s = "{\n" +
                "\"place_id\": \"" + place_id + "\",\n" +
                "\"address\":\"Megha Dubey Address\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}";
        return s;
    }

    public static String deleteReqBody(String place_id) {
        String s = "{\n" +
                "    \"place_id\":\"" + place_id + "\"\n" +
                "}";

        return s;
    }
}

package Utilities;

import io.restassured.path.json.JsonPath;

import java.util.HashMap;
import java.util.List;

public class ParseJsonUtility {

    private static JsonPath convertToJson(String response) {
        return new JsonPath(response);
    }

    public static List<HashMap> responseList(String response) {
        return convertToJson(response).get();
    }
}

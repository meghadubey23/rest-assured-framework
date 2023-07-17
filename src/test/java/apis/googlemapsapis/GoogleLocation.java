package apis.googlemapsapis;

public class GoogleLocation {

    private static String lat;
    private static String lng;

    public static String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public static String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}

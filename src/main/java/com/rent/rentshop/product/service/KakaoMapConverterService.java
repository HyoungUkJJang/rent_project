package com.rent.rentshop.product.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Value;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class KakaoMapConverterService {

    @Value("${kakao.key}")
    private String key;

    public String toAddr(String map_x, String map_y) {
        String url = "https://dapi.kakao.com/v2/local/geo/coord2address.json?";
        String x = "x="+map_x;
        String y = "&y=" + map_y;

        url = url + x + y;
        String addr = "";
        try {
            addr = getRegionAddress(getJSONData(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addr;
    }

    public String getJSONData(String apiUrl) throws Exception {
        String jsonString = new String();
        String buf;
        URL url = new URL(apiUrl);
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        String auth = "KakaoAK " + key;
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Requested-With", "curl");
        conn.setRequestProperty("Authorization", auth);

        BufferedReader bf = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        while ((buf = bf.readLine()) != null) {
            jsonString += buf;
        }
        return jsonString;
    }

    public String getRegionAddress(String jsonString) {

        String depth_1 = "";
        String depth_2 = "";
        String value = "";
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject meta = (JSONObject)jsonObject.get("meta");
        long size = (long) meta.get("total_count");

        if (size > 0) {
            JSONArray jsonArray = (JSONArray) jsonObject.get("documents");
            JSONObject regionObject = (JSONObject) jsonArray.get(0);
            JSONObject roadAddress = (JSONObject) regionObject.get("road_address");
            if (roadAddress != null) {
                depth_1 = (String) roadAddress.get("region_1depth_name");
                depth_2 = (String) roadAddress.get("region_2depth_name");
                value = depth_1 + " " + depth_2;
                return value;
            } else {
                JSONObject address = (JSONObject) regionObject.get("address");
                depth_1 = (String) roadAddress.get("region_1depth_name");
                depth_2 = (String) roadAddress.get("region_2depth_name");
                value = depth_1 + " " + depth_2;
                return value;
            }
        }

        return "주소가 이상해요 ㅠㅠ";

    }

}

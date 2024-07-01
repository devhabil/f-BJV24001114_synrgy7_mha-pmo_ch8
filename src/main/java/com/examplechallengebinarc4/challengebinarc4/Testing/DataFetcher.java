package com.examplechallengebinarc4.challengebinarc4.Testing;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DataFetcher {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://dev.farizdotid.com/api/daerahindonesia/provinsi",
                HttpMethod.GET,
                null,
                String.class


        );
        if (exchange.getStatusCode().is2xxSuccessful()) {
            String responseBody = exchange.getBody();
            JSONObject jsonObject = new JSONObject(responseBody);
            JSONArray provinsiArray = jsonObject.getJSONArray("provinsi");
            for (int i = 0; i < provinsiArray.length(); i++) {
                JSONObject provinsiObj = provinsiArray.getJSONObject(i);
                long id = provinsiObj.getLong("id");
                String nama = provinsiObj.getString("nama");
                System.out.println("ID: " + id + ", Nama: " + nama);
            }
        } else {
            System.out.println("Gagal mendapatkan data. Status: " + exchange.getStatusCodeValue());
        }
    }
}

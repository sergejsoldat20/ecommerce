package com.example.customersupport.services;

import com.example.customersupport.beans.AdminBean;
import com.example.customersupport.dto.AuthResponse;
import com.example.customersupport.dto.LoginRequest;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthService implements Serializable {

    public AdminBean authenticate(String username, String password)
            throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        LoginRequest payload= new LoginRequest(username, password);
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/api/auth/authenticate"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/api/auth/validate"))
                .GET()
                // .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + gson.fromJson(response.body(), AuthResponse.class).token)
                .build();

        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        return gson.fromJson(response.body(), AdminBean.class);

    }
}
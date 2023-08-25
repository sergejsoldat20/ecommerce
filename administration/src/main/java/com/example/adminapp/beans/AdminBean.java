package com.example.adminapp.beans;

import com.example.adminapp.dto.AuthResponse;
import com.example.adminapp.dto.LoginRequest;
import com.example.adminapp.models.Admin;
import com.example.adminapp.models.enums.Role;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AdminBean implements Serializable {
    private boolean isLoggedIn = false;

    /*public boolean login(String username, String password) {
        Admin admin = AdminDAO.selectAdmin(username, password);
        if (admin != null) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }*/

    public Boolean authenticate(String username, String password)
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


        Admin account = gson.fromJson(response.body(), Admin.class);

        if(account != null && account.getRole().equals(Role.ADMIN)) {
            isLoggedIn = true;
            return true;
        } else{
            return false;
        }
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
package com.example.adminapp.beans;

import com.example.adminapp.dto.AccountRequest;
import com.example.adminapp.dto.RegisterRequest;
import com.example.adminapp.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class UserBean implements Serializable {

    private User user;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<User> getAll() throws IOException, InterruptedException, URISyntaxException {
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/admin-app/accounts"))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), listType);
    }

    public User getById(int id) throws URISyntaxException, IOException, InterruptedException {

        Gson gson = new Gson();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/admin-app/accounts/" + id))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), User.class);
    }


    public boolean addUser(RegisterRequest user) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/admin-app/insert-account"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return true;
    }

    public boolean update(AccountRequest user) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/admin-app/update-account/" + user.getId()))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
       return true;
    }

    public void deleteAccount(Integer accountId) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/admin-app/delete-account/" + accountId))
                .DELETE()
                //.header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
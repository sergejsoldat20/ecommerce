package com.example.customersupport.services;

import com.example.customersupport.beans.MessageBean;
import com.example.customersupport.dto.SendEmailRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Properties;

public class MessageService implements Serializable {

    public List<MessageBean> getUnreadMessages()
            throws URISyntaxException, IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newHttpClient();

        Type listType = new TypeToken<List<MessageBean>>() {
        }.getType();
        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/report-messages/admin/unread"))
                .GET()
                // .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<MessageBean> payload = gson.fromJson(response.body(), listType);
        payload.forEach(System.out::println);

        return payload;
    }

    public void sendMessage(String email, String messageText, Integer messageId) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();


        Gson gson = new Gson();
        SendEmailRequest payload= new SendEmailRequest(email, messageText, messageId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:9000/report-messages/admin/send-email"))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(payload)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
    }

    public MessageBean getById(Integer id) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();


        Gson gson = new Gson();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(String.format("http://localhost:9000/report-messages/admin/%s", id)))
                .GET()
                // .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


        return gson.fromJson(response.body(), MessageBean.class);
    }

}
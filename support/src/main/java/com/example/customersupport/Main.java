package com.example.customersupport;

import com.example.customersupport.services.AuthService;
import com.example.customersupport.services.MessageService;

import javax.mail.Message;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, URISyntaxException, IOException, InterruptedException {
//        try {
//            System.out.println(new AuthService().authenticate("sega", "pass"));
//        } catch (URISyntaxException | IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        System.out.println(new MessageService().getById(13));
    }
}
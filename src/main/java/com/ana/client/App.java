package com.ana.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends JFrame {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String WINDOW_TITLE = "Super Innovative Cookie Code";
    private static final String BASE_URL = "http://localhost:8080";
    private static final String EXIT_ENDPOINT = BASE_URL + "/exit";

    public App() {
        super(WINDOW_TITLE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                String requestBody = "Server exit message";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.TEXT_PLAIN);

                HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

                try {
                    restTemplate.postForObject(EXIT_ENDPOINT, request, String.class);
                } catch (Exception ex) {
                    System.out.println("The server isn't running.");
                }

                super.windowClosing(e);
            }
        });

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}

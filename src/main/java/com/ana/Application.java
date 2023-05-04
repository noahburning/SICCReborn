package com.ana;

import com.ana.client.gui.ClientFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientFrame::getInstance);
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

}

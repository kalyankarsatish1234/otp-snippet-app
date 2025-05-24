package com.otp_snippet_app.controller;

import com.otp_snippet_app.entity.Snippet;
import com.otp_snippet_app.service.SnippetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class SnippetController {
    private final SnippetService service;

    public SnippetController(SnippetService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/submit")
    public String submitSnippet(@RequestParam String content, Model model) {
        if (content == null || content.trim().isEmpty()) {
            model.addAttribute("error", "Please enter some text before generating an OTP.");
            return "index";
        }
        String otp = service.saveSnippet(content);
        model.addAttribute("otp", otp);
        return "index";
    }

    @GetMapping("/retrieve")
    public String retrieveForm() {
        return "retrieve";
    }

    @PostMapping("/get")
    public String getSnippet(@RequestParam String otp, Model model) {
        Optional<String> content = service.getSnippetByOtp(otp);
        if (content.isPresent()) {
            model.addAttribute("content", content.get());
        } else {
            model.addAttribute("error", "OTP is invalid, expired, or already used.");
        }
        return "retrieve";
    }
}

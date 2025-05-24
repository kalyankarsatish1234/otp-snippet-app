package com.otp_snippet_app.service;

import com.otp_snippet_app.entity.Snippet;
import com.otp_snippet_app.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class SnippetService {
    private final SnippetRepository repository;
    private final int EXPIRATION_MINUTES = 10;

    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public String saveSnippet(String content) {
        String otp = generate4DigitOtp();
        Snippet snippet = Snippet.builder().otp(otp).content(content).createdAt(LocalDateTime.now()).used(false).build();

        repository.save(snippet);
        return otp;
    }

    private String generate4DigitOtp() {
        Random random = new Random();
        int otpValue = 1000 + random.nextInt(9000);
        return String.valueOf(otpValue);
    }

    public Optional<String> getSnippetByOtp(String otp) {
        Optional<Snippet> optional = repository.findById(otp);
        if (optional.isEmpty()) return Optional.empty();

        Snippet snippet = optional.get();

        // Check expiration and usage
        if (snippet.isUsed()) return Optional.empty();
        if (snippet.getCreatedAt().plusMinutes(EXPIRATION_MINUTES).isBefore(LocalDateTime.now()))
            return Optional.empty();

        snippet.setUsed(true);
        repository.save(snippet);
        return Optional.of(snippet.getContent());
    }
}

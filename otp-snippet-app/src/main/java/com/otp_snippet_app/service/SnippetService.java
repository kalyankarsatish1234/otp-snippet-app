package com.otp_snippet_app.service;

import com.otp_snippet_app.entity.Snippet;
import com.otp_snippet_app.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class SnippetService {
    private final SnippetRepository repository;
    private final int EXPIRATION_MINUTES = 10;

    public SnippetService(SnippetRepository repository) {
        this.repository = repository;
    }

    public String saveSnippet(String content) {
        String otp = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Snippet snippet = Snippet.builder().otp(otp).content(content).createdAt(LocalDateTime.now()).used(false).build();
        repository.save(snippet);
        return otp;
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

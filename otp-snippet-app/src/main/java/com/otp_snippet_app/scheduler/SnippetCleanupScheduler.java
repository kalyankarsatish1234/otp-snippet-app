package com.otp_snippet_app.scheduler;

import com.otp_snippet_app.repository.SnippetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SnippetCleanupScheduler {

    private final SnippetRepository repository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteOldSnippets() {
        repository.findAll().forEach(snippet -> {
            if (snippet.getCreatedAt().plusHours(24).isBefore(LocalDateTime.now())) {
                repository.delete(snippet);
            }
        });
        System.out.println("Cleaned expired snippets at: " + LocalDateTime.now());
    }
}

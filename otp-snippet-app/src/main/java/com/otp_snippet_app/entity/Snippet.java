package com.otp_snippet_app.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "snippets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Snippet {

    @Id
    @Column(name = "otp", nullable = false, length = 6)
    private String otp;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "used", nullable = false)
    private boolean used;
}
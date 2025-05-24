package com.otp_snippet_app.repository;

import com.otp_snippet_app.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, String> {
}
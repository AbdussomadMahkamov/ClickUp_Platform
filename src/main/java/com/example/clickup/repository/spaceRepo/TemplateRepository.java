package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> findByNomi(String nomi);
}

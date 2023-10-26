package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.Template;
import com.example.clickup.entitiy.space.TemplateStatusThirdTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateStatusThirdTableRepository extends JpaRepository<TemplateStatusThirdTable, Long> {
}

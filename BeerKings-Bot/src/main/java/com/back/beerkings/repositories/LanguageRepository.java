package com.back.beerkings.repositories;

import com.back.beerkings.models.database.LanguageMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<LanguageMO, Long> {
}

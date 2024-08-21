package com.back.beerkings.repositories;

import com.back.beerkings.models.database.PodiumMO;
import com.back.beerkings.models.database.PodiumPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PodiumRepository extends JpaRepository<PodiumMO, PodiumPK> {

    List<PodiumMO> findAllByChatId (Long id);
}

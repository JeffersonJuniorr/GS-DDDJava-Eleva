package com.db.eleva.repository;

import com.db.eleva.model.TrilhaDeAprendizagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrilhaDeAprendizagemRepository extends JpaRepository<TrilhaDeAprendizagem, Long> {
}

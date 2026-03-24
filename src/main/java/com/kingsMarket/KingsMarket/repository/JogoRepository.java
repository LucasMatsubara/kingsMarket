package com.kingsMarket.KingsMarket.repository;

import com.kingsMarket.KingsMarket.model.JogoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<JogoModel, Long> {
    List<JogoModel> findByDesenvolvedoraId(Long desenvolvedoraId);
}
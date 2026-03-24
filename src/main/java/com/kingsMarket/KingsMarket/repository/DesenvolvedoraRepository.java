package com.kingsMarket.KingsMarket.repository;

import com.kingsMarket.KingsMarket.model.DesenvolvedoraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesenvolvedoraRepository extends JpaRepository<DesenvolvedoraModel, Long> {
}
package com.mobiledevice.repositories;

import com.mobiledevice.entities.CellphoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellphoneRepository extends JpaRepository<CellphoneEntity, Long> {
}

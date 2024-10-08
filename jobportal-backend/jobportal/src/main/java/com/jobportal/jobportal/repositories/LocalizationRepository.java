package com.jobportal.jobportal.repositories;

import com.jobportal.jobportal.entities.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalizationRepository extends JpaRepository<Localization, Long> {

    @Query(value = "SELECT l.id, l.lat, l.lng, o.id FROM localization l LEFT JOIN offer o ON l.id = o.localization_id", nativeQuery = true)
    List<Object[]> findLocalizationIdLatLngAndOfferIds();


}

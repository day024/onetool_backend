package com.onetool.server.api.blueprint.repository;

import com.onetool.server.api.blueprint.Blueprint;
import com.onetool.server.api.blueprint.InspectionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlueprintRepository extends JpaRepository<Blueprint, Long> {

    @Query(value = "SELECT b FROM Blueprint b WHERE (b.blueprintName LIKE %:keyword% OR b.creatorName LIKE %:keyword%) AND b.inspectionStatus = :status")
    Page<Blueprint> findAllNameAndCreatorContaining(@Param("keyword") String keyword, @Param("status") InspectionStatus status, Pageable pageable);

    @Query(value = "SELECT b FROM Blueprint b WHERE b.categoryId = " +
            "(SELECT f.id FROM FirstCategory f WHERE f.name = :first) " +
            "AND b.inspectionStatus = :status")
    Page<Blueprint> findAllByFirstCategory(@Param("first") String category, @Param("status") InspectionStatus status, Pageable pageable);

    @Query(value = "SELECT b FROM Blueprint b WHERE b.secondCategory = :second " +
            "AND b.categoryId = (SELECT f.id FROM FirstCategory f WHERE f.name = :first) " +
            "AND b.inspectionStatus = :status")
    Page<Blueprint> findAllBySecondCategory(@Param("first") String firstCategory, @Param("second") String secondCategory, @Param("status") InspectionStatus status, Pageable pageable);

    @Query(value = "SELECT count(*) FROM Blueprint b")
    Long countAllBlueprint();

    @Query(value = "SELECT b FROM Blueprint b WHERE b.inspectionStatus = :status")
    List<Blueprint> findByInspectionStatus(@Param("status") InspectionStatus status);
}
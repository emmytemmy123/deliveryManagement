package Salvation.Clinic.repo.drugRepo;


import Salvation.Clinic.model.entity.drug.DrugCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrugCategoryRepo extends JpaRepository<DrugCategory, Long> {

    @Query("select st from DrugCategory st where st.uuid = :recordId ")
    Optional<DrugCategory> findByUuid (@Param("recordId") UUID uuid);

    @Query("delete from DrugCategory st where st.uuid=:recordId")
    Optional<DrugCategory> deleteByUuid (@Param("recordId") UUID uuid);

    @Query("select st from DrugCategory st where st.name=:name")
    Optional<DrugCategory> findCategoryByName (@Param("name") String name);


}

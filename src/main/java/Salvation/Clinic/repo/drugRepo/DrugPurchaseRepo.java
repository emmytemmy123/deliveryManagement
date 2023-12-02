package Salvation.Clinic.repo.drugRepo;


import Salvation.Clinic.model.entity.drug.DrugPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrugPurchaseRepo extends JpaRepository<DrugPurchase, Long> {

    @Query("select st from DrugPurchase st where st.uuid=:recordId")
    Optional<DrugPurchase> findByUuid (@Param("recordId")UUID uuid);

    @Query("delete from DrugPurchase st where st.uuid=:recordId")
    Optional<DrugPurchase> deleteByUuid(@Param("recordId") UUID uuid);

    @Query("select st from DrugPurchase st where st.category=:category")
    Optional<DrugPurchase> findByCategory (@Param("category") String category);

    Optional  <DrugPurchase> findByName(String name);


}

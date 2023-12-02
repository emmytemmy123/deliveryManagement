package Salvation.Clinic.repo.transactionRepo;

import Salvation.Clinic.model.entity.transaction.DrugOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DrugOrderItemsRepo extends JpaRepository<DrugOrderItems, Long> {

    @Query("select st from DrugOrderItems st where st.uuid=:uuid")
    Optional<DrugOrderItems> findByUuid(@Param("uuid") UUID uuid);

    @Query("delete from DrugOrderItems st where st.uuid=:uuid")
    Optional<DrugOrderItems> deleteByUuid (@Param("uuid") UUID uuid);



 }

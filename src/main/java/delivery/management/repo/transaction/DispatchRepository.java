package delivery.management.repo.transaction;


import delivery.management.model.entity.transaction.Delivery;
import delivery.management.model.entity.transaction.DispatchDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DispatchRepository extends JpaRepository<DispatchDriver, Long> {

    @Query("select st from DispatchDriver st where st.uuid=:recordId")
    Optional<DispatchDriver> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from DispatchDriver st where st.uuid=:recordId")
    Optional<DispatchDriver> deleteByUuid(@Param("recordId")UUID uuid);

    @Query("select st from DispatchDriver st where st.deliveryNo=:deliveryNo")
    Optional<DispatchDriver> findDispatchByDeliveryNo(@Param("deliveryNo") String deliveryNo);

    @Query("select st from DispatchDriver st where st.dispatchName=:dispatchName")
    List<DispatchDriver> findDispatchByDispatchName(@Param("dispatchName") String dispatchName);

    @Query("select st from DispatchDriver st where st.email=:email")
    Optional<DispatchDriver> findDispatchByEmail(@Param("email") String email);

//    //    @Query("select st from Delivery st where st.dateCreated=:dateCreated")
//    @Query("SELECT p FROM Delivery p WHERE p.deliveryDate LIKE CONCAT('%',:query, '%')")
//    List<Delivery> searchDeliveryByDateCreated(String query);
//
//    @Query( value = "select * from delivery where month(date_created) =:month and year(date_created) =:year order by id Desc limit 1", nativeQuery = true)
//    List<Delivery> findDeliveryForCurrentDate(@Param("month")Integer month, @Param("year") Integer year);


}

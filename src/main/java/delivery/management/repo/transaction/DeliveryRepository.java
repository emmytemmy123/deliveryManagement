package delivery.management.repo.transaction;


import delivery.management.model.entity.transaction.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select st from Delivery st where st.uuid=:recordId")
    Optional<Delivery> findByUuid(@Param("recordId") UUID uuid);

    @Query("delete from Delivery st where st.uuid=:recordId")
    Optional<Delivery> deleteByUuid(@Param("recordId")UUID uuid);

    @Query("select st from Delivery st where st.users.uuid=:usersUuid")
    List<Delivery> findDeliveryBySender(@Param("usersUuid") UUID usersUuid);

////    @Query("select st from Delivery st where st.dateCreated=:dateCreated")
//    @Query("SELECT p FROM Delivery p WHERE p.deliveryDate LIKE CONCAT('%',:query, '%')")
//    List<Delivery> searchDeliveryByDateCreated(String query);

    @Query( value = "select * from delivery where month(date_created) =:month and year(date_created) =:year order by id Desc limit 1", nativeQuery = true)
    List<Delivery> findDeliveryForCurrentDate(@Param("month")Integer month, @Param("year") Integer year);



}

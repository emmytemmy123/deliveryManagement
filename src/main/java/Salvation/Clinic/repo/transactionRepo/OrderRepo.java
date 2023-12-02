package Salvation.Clinic.repo.transactionRepo;

import Salvation.Clinic.model.entity.transaction.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Long> {

    @Query("select st from Orders st where st.uuid=:uuid")
    Optional<Orders> findByUuid(@Param("uuid")UUID uuid);

    @Query("delete from Orders st where st.uuid=:uuid")
    Optional<Orders> deleteByUuid (@Param("uuid") UUID uuid);

    @Query("select st from Orders st where st.orderBy=:orderBy")
    Optional<Orders> findByOrderBy (@Param("orderBy") String orderBy);

    @Query( value = "select * from orders where month(date_created) =:month and year(date_created) =:year order by id Desc limit 1", nativeQuery = true)
    List<Orders> findOrderForCurrentDate(@Param("month")Integer month, @Param("year") Integer year);


}

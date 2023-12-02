package Salvation.Clinic.repo.transactionRepo;


import Salvation.Clinic.model.entity.transaction.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query("select st from Payment st where st.uuid=:uuid")
    Optional<Payment> findByUuid (@Param("uuid")UUID uuid);

    @Query("delete from Payment st where st.uuid=:uuid")
    Optional<Payment> deleteByUuid (@Param("uuid")UUID uuid);

//    @Query("select st from Payment st where st.postedBy=:postedBy")
//    Optional<Payment> paidTo (@Param("postedBy")String postedBy);

    @Query(value = "select st from payment WHERE date_created BETWEEN:start_date AND :end_date", nativeQuery = true)
    List<Payment> findByDateRange(String start_date, String end_date );




}

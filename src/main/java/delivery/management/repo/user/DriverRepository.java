package delivery.management.repo.user;


import delivery.management.model.entity.user.Driver;
import delivery.management.model.entity.user.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByUsername(String username);

    @Query("select st from Driver st where st.uuid=:recordId")
    Optional<Driver> findByUuid(@Param("recordId") UUID uuid);

    @Query("select e from Driver e where e.email=:email")
    Optional<Driver> findByEmailId(@Param("email")String email);

    Driver findByEmail(String email);


}

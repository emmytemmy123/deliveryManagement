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
public interface SenderRepository extends JpaRepository<Sender, Long> {

    Sender findByUsername(String username);

    @Query("select st from Sender st where st.uuid=:recordId")
    Optional<Sender> findByUuid(@Param("recordId") UUID uuid);

    @Query("select e from Sender e where e.email=:email")
    Optional<Sender> findByEmailId(@Param("email")String email);

    Sender findByEmail(String email);

}

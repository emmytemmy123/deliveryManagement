package delivery.management.repo.user;

import delivery.management.model.entity.user.UsersType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsersTypeRepository extends JpaRepository <UsersType, Long> {


    @Query("select st from UsersType st where st.uuid=:recordId")
    Optional<UsersType> findByUuid(@Param("recordId") UUID uuid);

    Optional<UsersType> findByName(String name);


}

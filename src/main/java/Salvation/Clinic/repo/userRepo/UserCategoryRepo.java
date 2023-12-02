package Salvation.Clinic.repo.userRepo;


import Salvation.Clinic.model.entity.users.UsersCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserCategoryRepo extends JpaRepository<UsersCategory, Long> {

    @Query("select st from UsersCategory st where st.uuid=:recordId")
    Optional<UsersCategory> findByUuid(@Param("recordId") UUID uuid);

    Optional<UsersCategory> findByName(String name);


}

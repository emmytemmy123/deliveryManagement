package delivery.management.services.user;

import delivery.management.model.entity.user.AppUser;

public interface UserServiceTest {

    public AppUser getUserById(int anyInt);

    public AppUser saveUser(AppUser user);

}
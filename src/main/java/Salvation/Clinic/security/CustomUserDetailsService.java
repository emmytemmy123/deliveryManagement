package Salvation.Clinic.security;


import Salvation.Clinic.model.entity.users.Users;
import Salvation.Clinic.repo.userRepo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

<<<<<<< HEAD:src/main/java/Salvation/Clinic/security/CustomUserDetailsService.java
        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(username));
=======
        Optional<Users> user = usersRepository.findUsersByUsername(userName);
>>>>>>> origin/master:src/main/java/delivery/management/config/CustomUserDetailsService.java

        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));    }


}

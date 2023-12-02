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

        Optional<Users> user = Optional.ofNullable(usersRepository.findByUsername(username));

        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " Not Found"));    }


}

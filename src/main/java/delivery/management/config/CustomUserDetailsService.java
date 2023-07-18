package delivery.management.config;


import delivery.management.model.entity.user.Users;
import delivery.management.repo.user.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Users> user = usersRepository.findUsersByUsername(userName);

        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(userName + " Not Found"));    }


}

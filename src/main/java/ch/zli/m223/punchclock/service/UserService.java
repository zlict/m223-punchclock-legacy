package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserService  implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), emptyList());
    }

    public User getByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public User save(User user){
        this.userRepository.saveAndFlush(user);

        return this.userRepository.findById(user.getId())
                .orElse(new User());
    }
}

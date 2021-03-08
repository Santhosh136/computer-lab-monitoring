package gct.it.computerlabmonitoring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import gct.it.computerlabmonitoring.entities.MyUserDetails;
import gct.it.computerlabmonitoring.entities.User;
import gct.it.computerlabmonitoring.repositories.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(userName);
        if(user == null) throw new UsernameNotFoundException("Username "+userName+" Not found please register!");
        return new MyUserDetails(user);
    }
}

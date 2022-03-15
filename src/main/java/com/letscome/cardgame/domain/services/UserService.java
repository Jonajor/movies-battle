package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.UserDto;
import com.letscome.cardgame.domain.dtos.UserResponseDetail;
import com.letscome.cardgame.domain.entities.UserEntity;
import com.letscome.cardgame.domain.exceptions.UserNotFoundException;
import com.letscome.cardgame.domain.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserEntity findUser(String username){
        var user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            throw new UserNotFoundException();
        }
        return user.get();
    }

    public UserResponseDetail createUser(UserDto userDto){
         var user = userRepository.save(UserEntity.builder()
                .username(userDto.getUsername())
                .password(bCryptPasswordEncoder().encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .build());

         return UserResponseDetail.builder().userId(user.getId()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = findUser(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(),
                new ArrayList<>());
    }
}

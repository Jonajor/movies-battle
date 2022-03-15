package com.letscome.cardgame.domain.services;

import com.letscome.cardgame.domain.dtos.UserDto;
import com.letscome.cardgame.domain.entities.UserEntity;
import com.letscome.cardgame.domain.exceptions.UserNotFoundException;
import com.letscome.cardgame.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void testCreateUser(){
        var user = new UserEntity();
        user.setId(1L);
        user.setUsername("teste");
        user.setEmail("teste@teste");
        user.setPassword("teste");
        when(userRepository.save(any())).thenReturn(user);

        var userDto = new UserDto();
        userDto.setUsername("teste");
        userDto.setEmail("teste@teste");
        userDto.setPassword("teste");
        var result = userService.createUser(userDto);
        assertEquals(result.getUserId(), user.getId());
    }

    @Test
    public void testLoadUserByUsername(){
        var user = new UserEntity();
        user.setId(1L);
        user.setUsername("teste");
        user.setEmail("teste@teste");
        user.setPassword("teste");
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
        var result = userService.loadUserByUsername(user.getUsername());
        assertEquals(result.getUsername(), user.getUsername());
        assertEquals(result.getPassword(), user.getPassword());
    }

    @Test
    public void testUserNotFoundException(){
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {
            userService.loadUserByUsername(any());
        });
    }
}

package me.thirdDuck.threeDuckBlog.blogDevelop.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.thirdDuck.threeDuckBlog.blogDevelop.domain.User;
import me.thirdDuck.threeDuckBlog.blogDevelop.dto.AddUserRequest;
import me.thirdDuck.threeDuckBlog.blogDevelop.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .build())
                .getId();
    }
}

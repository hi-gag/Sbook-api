package com.highgag.sbook.user.service;

import com.highgag.sbook.error.DuplicatedUserException;
import com.highgag.sbook.user.domain.User;
import com.highgag.sbook.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user){
        validateDuplicateMember(user);
        userRepository.save(user);
    }


    private void validateDuplicateMember(User user){
        Optional<User> findMember = userRepository.findByEmail(user.getEmail());
        if (findMember.isPresent()){
            throw new DuplicatedUserException();
        }
    }
}

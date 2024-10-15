package com.bupjangsa.service;

import com.bupjangsa.domain.user.User;
import com.bupjangsa.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository 에서 데이터 처리 후 DTO를 리턴합니다.
 *
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public void registUser(User user){
        Optional<User> userInfo = userRepository.findByUserId(user.getUserId());
        if(userInfo.isPresent()){
            //TODO 예외 정리
            throw new RuntimeException("이미 존재하는 유저");
        }

        userRepository.save(user);
    }

    public void updateUser(User user){

        userRepository.findById(Long.parseLong(user.getUserId()))
                .orElseThrow(() -> new RuntimeException(""));

    }

    public Optional<User> findUser(Long userId){
        return Optional.of(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("")));
    }

}

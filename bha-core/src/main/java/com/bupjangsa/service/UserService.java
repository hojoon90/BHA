package com.bupjangsa.service;

import com.bupjangsa.domain.user.dto.UserDto;
import com.bupjangsa.domain.user.entity.User;
import com.bupjangsa.domain.user.infra.UserRepository;
import com.bupjangsa.exception.UserDataException;
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

    @Transactional
    public void registerUser(UserDto.Register userDto){
        User user = userDto.toEntity();
        Optional<User> userInfo = userRepository.findByAccountId(user.getAccountId());
        if(userInfo.isPresent()){
            throw new UserDataException("이미 존재하는 유저입니다.");
        }

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserDto.Update userDto){
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserDataException("유저를 조회할 수 없습니다."));

        user.updateUserData(userDto);
    }

    public UserDto.UserInfo findUser(Long id){
        return userRepository.findById(id)
                .map(UserDto.UserInfo::of)
                .orElseThrow(() -> new UserDataException("유저를 조회할 수 없습니다."));
    }

    public UserDto.UserInfo findUserByAccountId(String userId){
        return userRepository.findByAccountId(userId)
                .map(UserDto.UserInfo::of)
                .orElseThrow(() -> new UserDataException("유저를 조회할 수 없습니다."));
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserDataException("유저를 조회할 수 없습니다."));
        user.updateSignOutDate();
        userRepository.delete(user);
    }

}

package com.ohgiraffers.testyoonkaotalk.talk.service;

import com.ohgiraffers.testyoonkaotalk.talk.dao.UserRepository;
import com.ohgiraffers.testyoonkaotalk.talk.dto.UserDTO;
import com.ohgiraffers.testyoonkaotalk.talk.embedded.Status;
import com.ohgiraffers.testyoonkaotalk.talk.embedded.Time;
import com.ohgiraffers.testyoonkaotalk.talk.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TalkService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public void regist(UserDTO userDTO) {
        userDTO.setStartTime(LocalDateTime.now());
        System.out.println("userDTO = " + userDTO);
        User user = new User(userDTO.getUserId(),
                userDTO.getUserPw(),
                userDTO.getUserName(),
                userDTO.getUserGender(),
                new Status("Y"),
                new Time(userDTO.getStartTime(),null));
        userRepository.save(user);
    }

    public List<UserDTO> getList() {

        List<User> userList = userRepository.findAll();

//        userList.forEach(user -> {
//            UserDTO userDTO = modelMapper.map(user,UserDTO.class);
//        });

        return userList.stream().map(m->modelMapper.map(m,UserDTO.class)).collect(Collectors.toList());
    }

    public void delete(int userCode) {
        userRepository.deleteById(userCode);
    }

    public User findbyId(int userCode) {
        return userRepository.findById(userCode).orElseThrow();
    }
}
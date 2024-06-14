package com.ohgiraffers.testyoonkaotalk.talk.dao;

import com.ohgiraffers.testyoonkaotalk.talk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}

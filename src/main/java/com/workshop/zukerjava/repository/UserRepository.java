package com.workshop.zukerjava.repository;

import com.workshop.zukerjava.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;

//TODO:用MongoRepository还是JpaRepository？
public interface UserRepository extends MongoRepository<User, String> {
    
    User findByUsername(String username);
    
}

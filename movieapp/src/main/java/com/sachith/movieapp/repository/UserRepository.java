package com.sachith.movieapp.repository;

import com.sachith.movieapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User save(User user);
    User findByUsernameAndPosition(String Username, String Position);

}

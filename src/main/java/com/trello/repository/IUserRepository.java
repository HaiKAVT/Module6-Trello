package com.trello.repository;

import com.trello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByUserNameAndAndEmail(String userName, String email);
    @Query(value = "select * from user u " + "where u.username " + "like ?1 or u.nickname like ?1", nativeQuery = true)
    Iterable<User> findUserByKeyword(String keyword);

    Boolean existsByUserName(String username);

    Boolean existsByEmail(String email);


}

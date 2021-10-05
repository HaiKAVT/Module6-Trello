package com.trello.service.user;

import com.trello.model.User;
import com.trello.service.GeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GeneralService<User>, UserDetailsService {
    User findByUserName(String userName);
    User findByUsernameAndEmail(String username, String email);
    Iterable<User> findUserByKeyword(String keyword);
    User update(User user);
}

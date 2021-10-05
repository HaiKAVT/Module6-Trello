package com.trello.service.role;

import com.trello.model.Role;
import com.trello.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService implements IRolesService{
    @Autowired
    IRoleRepository rolrrepository;
    @Override
    public Iterable<Role> findAll() {
        return rolrrepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return rolrrepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return rolrrepository.save(role);
    }

    @Override
    public void deleteById(Long id) {
        rolrrepository.deleteById(id);
    }
}

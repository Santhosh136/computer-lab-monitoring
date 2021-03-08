package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer>{
    User findByUserName(String userName);
}


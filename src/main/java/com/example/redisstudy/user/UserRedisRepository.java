package com.example.redisstudy.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<User, String> {

}

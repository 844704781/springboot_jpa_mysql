package com.example.example1.dao;

import com.example.example1.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends MyBaseRepository<User,Long> {
}

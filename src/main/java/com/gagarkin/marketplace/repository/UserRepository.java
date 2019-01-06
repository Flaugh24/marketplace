package com.gagarkin.marketplace.repository;

import com.gagarkin.marketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}

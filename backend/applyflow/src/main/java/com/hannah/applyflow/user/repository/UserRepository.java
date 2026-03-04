package com.hannah.applyflow.user.repository;

import com.hannah.applyflow.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}

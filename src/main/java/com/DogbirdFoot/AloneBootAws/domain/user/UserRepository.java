package com.DogbirdFoot.AloneBootAws.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<NormalUser,Long> {
    Optional<NormalUser> findByEmail(String email);
}

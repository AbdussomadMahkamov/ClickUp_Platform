package com.example.clickup.repository.spaceRepo;

import com.example.clickup.entitiy.space.SpaceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpaceUserRepository extends JpaRepository<SpaceUser, UUID> {
    Optional<SpaceUser> findBySpaceIdAndUsersId(Long space_id, UUID users_id);
    List<SpaceUser> findBySpaceId(Long space_id);
}

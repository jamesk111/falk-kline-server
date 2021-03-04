package com.falkkline.server.jpa;

import com.falkkline.server.entities.GuestListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestListRepository extends JpaRepository<GuestListEntity, Long> {
}

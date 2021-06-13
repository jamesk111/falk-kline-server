package com.falkkline.server.jpa;

import com.falkkline.server.entities.GuestListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestListRepository extends JpaRepository<GuestListEntity, Long> {
	void deleteAllByParentId(Long id);

	boolean existsByInviteCode(String inviteCode);

	GuestListEntity getByInviteCode(String inviteCode);
	List<GuestListEntity> findAllByParentId(Long id);
}

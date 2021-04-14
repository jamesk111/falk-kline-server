package com.falkkline.server.services;

import com.falkkline.server.jpa.GuestListRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {

	private final GuestListRepository guestListRepository;

	@Autowired
	public InviteCodeServiceImpl(GuestListRepository guestListRepository) {
		this.guestListRepository = guestListRepository;
	}

	@Override
	public String generate() {
		String generatedString;

		do {
			generatedString = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		} while (guestListRepository.existsByInviteCode(generatedString));

		return generatedString;
	}

	@Override
	public boolean isValid(String inviteCode) {
		return guestListRepository.existsByInviteCode(inviteCode);
	}
}

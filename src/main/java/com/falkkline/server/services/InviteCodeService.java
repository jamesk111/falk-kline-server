package com.falkkline.server.services;

public interface InviteCodeService {
	String generate();
	boolean isValid(String code);
}

package com.falkkline.server.models;

import lombok.Data;

@Data
public class RsvpRequest {
	public long id;
	public String inviteCode;
	public boolean attending;
}

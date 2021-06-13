package com.falkkline.server.models;

import com.falkkline.server.entities.GuestListEntity;
import lombok.Data;

import java.util.List;

@Data
public class AdditionalGuestRequest {
	public long parentId;
	public String inviteCode;
	public List<GuestListEntity> guests;
	public List<Long> deleted;
}

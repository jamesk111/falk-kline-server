package com.falkkline.server.models.wedding;

import com.falkkline.server.models.Contact;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class WeddingGuest extends Contact {
	private String role;
	private Date reservedDate;
	private boolean attending;
}

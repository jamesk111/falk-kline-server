package com.falkkline.server.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "guest_list", schema = "public", catalog = "dap9htsjppm79q")
public class GuestListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Basic
	@Column(name = "first_name")
	private String firstName;

	@Basic
	@Column(name = "last_name")
	private String lastName;

	@Basic
	@Column(name = "address1")
	private String address1;

	@Basic
	@Column(name = "address2")
	private String address2;

	@Basic
	@Column(name = "city")
	private String city;

	@Basic
	@Column(name = "state")
	private String state;

	@Basic
	@Column(name = "zip")
	private Integer zip;

	@Basic
	@Column(name = "phone")
	private String phone;

	@Basic
	@Column(name = "email")
	private String email;

	@Basic
	@Column(name = "guest_role")
	private String guestRole;

	@Basic
	@Column(name = "reserved_date")
	private Timestamp reservedDate;

	@Basic
	@Column(name = "attending")
	private Boolean attending;

	@Basic
	@Column(name = "parent_id")
	private Long parentId;

	@Basic
	@Column(name = "invite_code")
	private String inviteCode;

	@Basic
	@Column(name = "allowed_plus_ones")
	private Integer allowedPlusOnes;

	@Basic
	@Column(name = "age_over_three")
	private Boolean ageOverThree;

	@Basic
	@Column(name = "guest_count")
	private Integer guestCount;
}

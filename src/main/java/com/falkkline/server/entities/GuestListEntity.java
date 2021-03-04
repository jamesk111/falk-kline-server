package com.falkkline.server.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GuestListEntity that = (GuestListEntity) o;
		return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(address1, that.address1) && Objects.equals(address2, that.address2) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zip, that.zip) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(guestRole, that.guestRole) && Objects.equals(reservedDate, that.reservedDate) && Objects.equals(attending, that.attending);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, address1, address2, city, state, zip, phone, email, guestRole, reservedDate, attending);
	}
}

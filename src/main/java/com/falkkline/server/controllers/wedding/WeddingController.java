package com.falkkline.server.controllers.wedding;

import com.falkkline.server.entities.GuestListEntity;
import com.falkkline.server.jpa.GuestListRepository;
import com.falkkline.server.services.InviteCodeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/api/wedding")
public class WeddingController {

	private final GuestListRepository guestListRepository;
	private final InviteCodeServiceImpl inviteCodeService;

	public WeddingController(GuestListRepository guestListRepository, InviteCodeServiceImpl inviteCodeService) {
		this.guestListRepository = guestListRepository;
		this.inviteCodeService = inviteCodeService;
	}

	@GetMapping("/test")
	public String test() {
		return inviteCodeService.generate();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody GuestListEntity guest) {
		if (guest.getParentId() == null && (guest.getInviteCode() == null || guest.getInviteCode().isEmpty())) {
			guest.setInviteCode(inviteCodeService.generate());
		}
		guestListRepository.save(guest);
	}

	@GetMapping("/guests")
	public List<GuestListEntity> list() {
		return guestListRepository.findAll();
	}

	@GetMapping("/guests/{id}")
	public GuestListEntity get(@PathVariable("id") long id) {
		return guestListRepository.getOne(id);
	}

	@DeleteMapping("/guests/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") long id) {
		guestListRepository.deleteById(id);
	}
}

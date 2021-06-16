package com.falkkline.server.controllers.wedding;

import com.falkkline.server.entities.GuestListEntity;
import com.falkkline.server.jpa.GuestListRepository;
import com.falkkline.server.models.AdditionalGuestRequest;
import com.falkkline.server.models.RsvpRequest;
import com.falkkline.server.services.InviteCodeServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

	@PostMapping("/addGuest")
	@ResponseStatus(HttpStatus.CREATED)
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

	@GetMapping("/guests/additional/{id}")
	public List<GuestListEntity> getAdditionalGuests(@PathVariable("id") long id) {
		return guestListRepository.findAllByParentId(id);
	}

	@PostMapping("/guests/additional")
	@ResponseStatus(HttpStatus.CREATED)
	public void createAdditionalGuest(@RequestBody AdditionalGuestRequest request) {
		long parentId = request.getParentId();
		String inviteCode = request.getInviteCode();
		GuestListEntity parent = guestListRepository.getOne(parentId);

		request.getDeleted().forEach(guestListRepository::deleteById);

		request.getGuests().forEach(guest -> {
			if (
					!guestListRepository.existsById(parentId)
							|| !parent.getInviteCode().equals(inviteCode)
							|| StringUtils.isBlank(guest.getFirstName())
							|| StringUtils.isBlank(guest.getLastName())
			) {
				return;
			}

			guest.setGuestRole("plusOne");
			guest.setAttending(true);
			guest.setParentId(parentId);
			guest.setInviteCode(null);
			guest.setAllowedPlusOnes(0);

			guestListRepository.save(guest);
		});
	}

	@GetMapping("/invite/{inviteCode}")
	public GuestListEntity getGuestByInviteCode(@PathVariable("inviteCode") String inviteCode) {
		GuestListEntity guest;
		try {
			guest = guestListRepository.getByInviteCode(inviteCode);
		} catch (NullPointerException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return guest;
	}

	@PostMapping("/rsvp")
	@Transactional
	public void rsvp(@RequestBody RsvpRequest rsvpRequest) {
		GuestListEntity guest;

		try {
			guest = guestListRepository.getByInviteCode(rsvpRequest.inviteCode);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		if (rsvpRequest.getId() != guest.getId() || !rsvpRequest.getInviteCode().equals(guest.getInviteCode())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		guest.setAttending(rsvpRequest.attending);
		guestListRepository.save(guest);

		if (!rsvpRequest.isAttending()) guestListRepository.deleteAllByParentId(guest.getId());
	}
}

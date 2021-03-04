package com.falkkline.server.controllers.wedding;

import com.falkkline.server.entities.GuestListEntity;
import com.falkkline.server.jpa.GuestListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wedding")
public class WeddingController {

	private final GuestListRepository guestListRepository;

	public WeddingController(GuestListRepository guestListRepository) {
		this.guestListRepository = guestListRepository;
	}

	@GetMapping("/guests")
	public List<GuestListEntity> list() {
		return guestListRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody GuestListEntity guest) {
		guestListRepository.save(guest);
	}

	@GetMapping("/guest/{id}")
	public GuestListEntity get(@PathVariable("id") long id) {
		return guestListRepository.getOne(id);
	}
}

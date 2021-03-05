package com.falkkline.server.controllers.wedding;

import com.falkkline.server.entities.GuestListEntity;
import com.falkkline.server.jpa.GuestListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wedding")
@CrossOrigin
public class WeddingController {

	private final GuestListRepository guestListRepository;

	public WeddingController(GuestListRepository guestListRepository) {
		this.guestListRepository = guestListRepository;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody GuestListEntity guest) {
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

package com.falkkline.server.controllers.wedding;

import com.falkkline.server.models.wedding.WeddingGuest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wedding")
public class WeddingController {

	@GetMapping("/guests")
	public List<WeddingGuest> list() {
		List<WeddingGuest> guests = new ArrayList<>();
		return guests;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody WeddingGuest guest) {

	}

	@GetMapping("/{id}")
	public WeddingGuest get(@PathVariable("id") long id) {
		return new WeddingGuest();
	}
}

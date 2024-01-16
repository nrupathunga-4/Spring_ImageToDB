package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.StorageService;

@RestController
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private StorageService service;
	
	
	@PostMapping
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException
	{
		String image = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK.value())
				.body(image);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<?> downloadImage(@PathVariable String name)
	{
		byte[] image = service.downloadImage(name);
		return ResponseEntity.status(HttpStatus.OK.value())
				.contentType(MediaType.valueOf("image/png"))
				.body(image);
	}
}

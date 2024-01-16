package com.example.demo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageData;
import com.example.demo.repository.ImageRepository;
import com.example.demo.util.ImageCompress;

@Service
public class StorageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	public String uploadImage(MultipartFile file) throws IOException
	{
		ImageData imageData = imageRepository.save(ImageData.builder()
				 .name(file.getOriginalFilename())
				 .type(file.getContentType())
				 .imageData(ImageCompress.compressImage(file.getBytes())).build());
			if(imageData!=null)
			{
				return "File Uploaded SuccessFully "+file.getOriginalFilename();
			}
			return null;
	}
	
	public byte[] downloadImage(String name)
	{
		Optional<ImageData> names = imageRepository.findByName(name);
		byte[] image = ImageCompress.decompressImage(names.get().getImageData());
		return image;
	}
	
}

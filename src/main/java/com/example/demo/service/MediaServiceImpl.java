package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Media;
import com.example.demo.repository.MediaRepository;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaRepository mediaRepository;
	
	@Override
	public List<Media> getAllMedia() {
		return mediaRepository.findAll();
	}

	@Override
	public Media saveMedia(Media media) {
		 return mediaRepository.save(media);	
	}


}

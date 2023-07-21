package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Image;
import com.example.demo.model.Media;

public interface MediaService {
	List<Media> getAllMedia();
	Media saveMedia(Media media);

}

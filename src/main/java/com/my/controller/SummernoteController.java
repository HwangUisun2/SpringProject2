package com.my.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SummernoteController {

	public static String uploadPath =
			"C:\\Users\\e\\eclipse-workspace\\SpringProject2\\src\\main\\resources\\static\\upload\\";
	
	
	public String upload(List<MultipartFile> multi){
		String sysFile="";
		UUID uuid = UUID.randomUUID();
		try {
			for(MultipartFile mf: multi) {
				if(mf.getOriginalFilename().equals("")) continue;
				String oriFile = mf.getOriginalFilename();
				sysFile = String.format("%s-%s",
						uuid.getLeastSignificantBits(), oriFile);
				File file = new File(uploadPath + sysFile);
				mf.transferTo(file);
				
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "./upload/" + sysFile;
	}
}







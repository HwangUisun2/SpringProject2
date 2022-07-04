package com.my.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.my.board.BoardAtt;

@Service
public class FileUploadController {

	public static String uploadPath =
			"C:\\Users\\e\\eclipse-workspace\\SpringProject2\\src\\main\\resources\\static\\upload\\";
	
	
	public List<BoardAtt> upload(List<MultipartFile> multi, int sno){
		List<BoardAtt> attList = new ArrayList<BoardAtt>();
		UUID uuid = UUID.randomUUID();
		try {
			for(MultipartFile mf: multi) {
				if(mf.isEmpty() ) continue;
				if(mf.getOriginalFilename().isEmpty()) continue;
				String oriFile = mf.getOriginalFilename();
				String sysFile = String.format("%s-%s",
						uuid.getLeastSignificantBits(), oriFile);
				File file = new File(uploadPath + sysFile);
				mf.transferTo(file);
				
				BoardAtt att = new BoardAtt();
				att.setOriFile(oriFile);
				att.setSysFile(sysFile);
				att.setpSno(sno);
				attList.add(att);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
		return attList;
	}
}







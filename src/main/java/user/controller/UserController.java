package user.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImageDTO;
import user.service.ObjectStorageService;
import user.service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-edu-bucket-92";
	
	@GetMapping(value = "uploadForm")
	public String uploadForm() {
		return "/user/uploadForm";
	}
	@GetMapping(value="uploadList")
	public String uploadList() {
		return "/user/uploadList";
	}
	
	// MappingJackson2HttpMessageConverter 가 jackson 라이브러리를 이용해
    // 자바 객체를 JSON 문자열로 변환하여 클라이언트로 보낸다.
    // 이 컨버터를 사용하면 굳이 UTF-8 변환을 설정할 필요가 없다.
    // 즉 produces = "application/json;charset=UTF-8" 를 설정하지 않아도 된다.
	@PostMapping(value = "upload")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO,
			@RequestPart("img[]") List<MultipartFile> list,
			HttpSession session) {

	//실제 폴더
	String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
	System.out.println("실제폴더 " + filePath);
	
	java.io.File file;
	String orginalFileName;
	String fileName;
	
	List<UserImageDTO> userImageList = new ArrayList<UserImageDTO>();
	
	for(MultipartFile img : list) {
	orginalFileName = img.getOriginalFilename();
	System.out.println(orginalFileName);
	
	fileName = objectStorageService.uploadFile(bucketName,"storage/", img);
	
	file = new File(filePath, orginalFileName);
	
	try {
	img.transferTo(file);
	} catch (Exception e) {
	e.printStackTrace();
	}
	
	UserImageDTO dto = new UserImageDTO();
	dto.setImageName(userImageDTO.getImageName());
	dto.setImageContent(userImageDTO.getImageContent());
	dto.setImageFileName(fileName);
	dto.setImageOriginalName(orginalFileName);
	
	userImageList.add(dto);
	}
	
	userService.upload(userImageList);
	
	return "image upload success";
	}
	@ResponseBody
	@PostMapping(value="getUploadList")
	public List<UserImageDTO> getUploadList() {
	return userService.getUploadList();
	}
}

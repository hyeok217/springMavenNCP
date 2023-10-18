package user.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import user.bean.UserImageDTO;
import user.service.ObjectStorageService;
import user.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-edu-bucket-105";
	
	@GetMapping(value="uploadForm")
	public String uploadForm() {
		return "/user/uploadForm";
	}
	
	@PostMapping(value="upload", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserImageDTO userImageDTO,
						 @RequestParam("img[]") List<MultipartFile> list,
						 HttpSession session) {
		
		//실제 폴더
		String filePath = session.getServletContext().getRealPath("/WEB-INF/storage");
		System.out.println("실제폴더 = " + filePath);
		
		File file;
		String originalFileName;
		String fileName;
		
		//파일명만 모아서 DB로 보내기
		List<UserImageDTO> userImageList = new ArrayList<UserImageDTO>();
		
		for(MultipartFile img : list) {
			originalFileName = img.getOriginalFilename();
			System.out.println(originalFileName);
			
			fileName = objectStorageService.uploadFile(bucketName, "storage/", img);
												//uuid형식으로 올리고 가져올 것임
			file = new File(filePath, originalFileName);
			
			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			UserImageDTO dto = new UserImageDTO();
			dto.setImageName(userImageDTO.getImageName()); //상품명
			dto.setImageContent(userImageDTO.getImageContent()); //상품 내용
			dto.setImageFileName(fileName); //UUID형식
			dto.setImageOriginalName(originalFileName);
			
			userImageList.add(dto);
			
		}//for
		
		//DB
		userService.upload(userImageList);
		
		return "이미지 등록 완료";
	}
	
	@GetMapping(value="uploadList")
	public String uploadList() {
			//동적처리 지금은 DB로 가서 데이터를 가져오지 않는다.
//		return	"WEB-INF/user/uploadList.jsp";	->servlet-Context에서 잡아줌
		return "/user/uploadList";
		
	}
	
	@PostMapping(value="getUploadList")
	@ResponseBody
	public List<UserImageDTO> getUploadList(){
		return userService.getUploadList();
	}
		
}












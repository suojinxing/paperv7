package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.service.FileService;
import cn.tedu.vo.JsonResult;

@RestController
@RequestMapping("/file/")
public class FileController {
	@Autowired
	private FileService fileService;

	@PostMapping("pic/uploadHeadImg")
	public String fileUpload(MultipartFile fileImage, String userId) {
		System.out.println(userId);
		System.out.println("FileController.fileUpload()" + fileImage);
		String picName = fileImage.getOriginalFilename();
		System.out.println(fileImage);
		fileService.fileUpload(fileImage, userId);

		JsonResult result = new JsonResult("upload ok");
		result.setData(picName);
		return "上传成功";
	}
}

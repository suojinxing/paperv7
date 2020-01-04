package cn.tedu.service;

import org.springframework.web.multipart.MultipartFile;

import cn.tedu.vo.EasyUIFile;

public interface FileService {

	EasyUIFile fileUpload(MultipartFile fileImage, String userId);

}

package cn.tedu.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.dao.UserHeadImageMapper;
import cn.tedu.pojo.UserHeadImage;
import cn.tedu.vo.EasyUIFile;
import lombok.Setter;

@Service
@Setter
@PropertySource("classpath:/properties/image.properties")
@ConfigurationProperties(prefix = "image")
public class FileServiceImpl implements FileService{
	@Autowired
	private UserHeadImageMapper userHeadImageMapper;
	
	private String localDir;	 // = "/Users/apple/Documents/paper/headImages/";
	private String localDirUrl;	 // = "http://image.score.com/";
	
	@Override
	public EasyUIFile fileUpload(MultipartFile uploadFile,String userId) {
		EasyUIFile easyUIFile = new EasyUIFile();

		// path: /Users/apple/Documents/jtImages
		// 1.判断文件是哦抚慰图片
		String regex = ".+\\.(jpg|png|gif|jpeg|psd)";
		String fileName = uploadFile.getOriginalFilename().toLowerCase();
		if (!fileName.matches(regex)) {
			// 不是图片上传失败
			return EasyUIFile.fail();
		}
		// 2.防止恶意程序
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if (width == 0 || height == 0) { // 若是木马程序则没有宽高属性
				return EasyUIFile.fail();
			}
			// 3.图片分文件保存
			String dateDir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date()); // 日期目录

//			String fileDirPath = localDir + dateDir;	// 本地保存的目录+日期目录|用以分割
			String fileDirPath = localDir;	// 本地保存的目录+日期目录|用以分割
			// 保存到数据库中路径
			UserHeadImage headImage = new UserHeadImage();
			headImage.setHeadImage(uploadFile.getOriginalFilename());
			headImage.setUserId(userId);
			userHeadImageMapper.insert(headImage);
			File dirFile = new File(fileDirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs(); // 目录不存在则创建目录
			}
			// 4. 生成文件名称,防止崇明
			// String picName = System.currentTimeMillis() + "" +
			String realFileName = UUID.randomUUID().toString() + fileName.substring(fileName.indexOf(".")); // 文件名称
			String picName = dirFile + "/" + realFileName;
			// 定义 URL
			String url = localDirUrl + dateDir + realFileName;

			uploadFile.transferTo(new File(picName));
			easyUIFile.setWidth(width).setHeight(height).setUrl(url);

		} catch (IOException e) {
			e.printStackTrace();
			return EasyUIFile.fail();
		}
		return easyUIFile;
	}
}

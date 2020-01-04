package cn.tedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.tedu.dao.UserHeadImageMapper;
import cn.tedu.pojo.UserHeadImage;

@Service
public class UserHeadImageServiceImpl implements UserHeadImageService{
	@Autowired
	private UserHeadImageMapper userHeadImageMapper;

	@Override
	public String findHeadImage(String userId) {
		QueryWrapper<UserHeadImage> queryWrapper = new QueryWrapper<UserHeadImage>();
		queryWrapper.eq("user_id", userId);
		String headImage = userHeadImageMapper.selectOne(queryWrapper).getHeadImage();
		return headImage;
	}

}

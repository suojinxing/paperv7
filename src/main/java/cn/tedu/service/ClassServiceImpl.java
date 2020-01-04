package cn.tedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.dao.ClassMapper;
import cn.tedu.exception.ServiceException;
import cn.tedu.pojo.Class;
import cn.tedu.util.ParamsUtil;

/**
 * 
 * @author HaiDi
 * @version 上午9:31:27
 * @email 2449365663@qq.com
 */
@Service
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassMapper classMapper;

	@Override
	public Class findById(String classId) {
		return classMapper.selectById(classId);
	}

	@Override
	public List<Class> findAll() {
		List<Class> classList = classMapper.selectAll();
		return classList;
	}

	@Override
	public void updateById(String id, String headmasterId,
			String headmasterName, String classSize, String liberalOrSciences) {
		Class class_ = new Class();
		if (ParamsUtil.regexParamsIsNullOrEmpty(id, headmasterName, classSize,
				headmasterId, liberalOrSciences)) {
			throw new ServiceException("参数均不能为空");
		}

		class_.setClassId(id);
		class_.setHeadmasterId(headmasterId);
		class_.setHeadmasterName(headmasterName);
		try {
			Integer.parseInt(classSize);
		}catch (Exception e) {
			throw new ServiceException("人数输入错误！");
		}
		class_.setClassSize(classSize);
		class_.setLiberalOrSciences(liberalOrSciences);
		// 1. 查询此id的班级是否存在,若存在则修改,若不存在则添加
		String classId = class_.getClassId();
		System.out.println("ClassServiceImpl.updateById() ===> " + classId);
		Class c = findById(classId);
		System.out.println("ClassServiceImpl.updateById() ===> " + c);
		if (c != null) {
			// 更新操作
			classMapper.updateById(class_);
		} else {
			// 添加操作
			addClass(class_);
		}
	}

	@Override
	public void addClass(Class class_) {
		classMapper.insertClass(class_);
	}

	@Override
	public void deleteById(String classId) {
		// 先判断是否勋在,若班级存在则删除,班级不存在抛出异常
		Class c = findById(classId);
		if (c != null) {
			classMapper.deleteById(classId);
		} else {
			throw new ServiceException("要删除的班级不存在");
		}
	}

}

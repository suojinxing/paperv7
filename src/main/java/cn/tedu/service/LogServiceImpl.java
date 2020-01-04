package cn.tedu.service;

import cn.tedu.dao.LogMapper;
import cn.tedu.vo.Log;
import cn.tedu.vo.PageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{
	@Autowired
	private LogMapper logMapper;

	@Override
	public void saveLog(Log log) {
		logMapper.insert(log);
	}

	@Override
	public List<Log> findLogs() {
		return logMapper.selectList(null);
	}

	@Override
	public PageObject<Log> findPageObjects(String username, Integer pageCurrent) {
		// 1. 判断参数是否合法
		if (pageCurrent == null || pageCurrent < 1) {
			pageCurrent = 1;
		}
		// 2. 查询结果
		int rows = logMapper.getRowCount();
		if (rows == 0) {
			throw new RuntimeException("没有学生记录");
		}

		int pageSize = 14;
		int startIndex = (pageCurrent - 1) * pageSize;
		List<Log> userList = logMapper.findPageObjects(username, pageCurrent,
				startIndex, pageSize);
		// 3. 包装查询到的结果
		PageObject<Log> pageObject = new PageObject<>();
		pageObject.setPageCount((rows - 1) / pageSize + 1);
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rows);
		pageObject.setRecords(userList);
		return pageObject;
	}
}

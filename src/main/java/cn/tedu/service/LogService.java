package cn.tedu.service;

import cn.tedu.vo.Log;
import cn.tedu.vo.PageObject;

import java.util.List;

public interface LogService{

	void saveLog(Log log);

	List<Log> findLogs();

	PageObject<Log> findPageObjects(String username, Integer pageCurrent);
}

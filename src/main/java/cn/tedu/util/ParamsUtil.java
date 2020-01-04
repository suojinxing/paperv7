package cn.tedu.util;

/**
 * 
 * @author HaiDi
 * @version 上午8:58:46
 * @email 2449365663@qq.com
 */
public class ParamsUtil {
	/**
	 * 验证参数为null或空
	 * 
	 * @param param
	 * @return
	 */
	public static boolean regexParamsIsNullOrEmpty(String param) {
		if (param == null || param == "") {
			return true;
		}
		return false;
	}

	/**
	 * 验证多个参数是否为null或空
	 * 
	 * @param params
	 * @return
	 */
	public static boolean regexParamsIsNullOrEmpty(String... params) {
		for (String param : params) {
			if (regexParamsIsNullOrEmpty(param)) {
				return true;
			}
		}
		return false;
	}

	public static boolean regexParamsIsNullOrEmpty(Object... params) {
		for (Object param : params) {
			if (param == null) {
				return true;
			}
		}
		return false;
	}

}

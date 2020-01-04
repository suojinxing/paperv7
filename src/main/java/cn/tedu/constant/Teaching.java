package cn.tedu.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 科目的map池
 * @author HaiDi
 * @version 下午7:10:15
 * @email 2449365663@qq.com
 */
public final class Teaching {
	private static final Map<String,String> teachingMap = new HashMap<String,String>();
	static {
		teachingMap.put("语文", "chineseScore");
		teachingMap.put("数学", "mathScore");
		teachingMap.put("英语", "englishScore");
		teachingMap.put("物理", "physicsScore");
		teachingMap.put("化学", "chemistryScore");
		teachingMap.put("生物", "biologyScore");
		teachingMap.put("政治", "politicsScore");
		teachingMap.put("地理", "geographyScore");
		teachingMap.put("历史", "historyScore");
	}
	
	public static String getTeachingToField(String teaching) {
		return teachingMap.get(teaching);
	}
}

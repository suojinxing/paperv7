package cn.tedu.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIFile implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer error = 0; // 判断是否有误
	private String url;
	private Integer width;
	private Integer height;

	// 为了简化操作提供静态方法
	public static EasyUIFile fail() {
		return new EasyUIFile(1, null, null, null);
	}

}

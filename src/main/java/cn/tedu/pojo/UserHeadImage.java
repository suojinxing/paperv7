package cn.tedu.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_head_image")
@Accessors(chain = true)
public class UserHeadImage {
	@TableId(type = IdType.AUTO)
	private Integer id;
	private String userId;
	private String headImage;
}

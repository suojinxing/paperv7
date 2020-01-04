package cn.tedu.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data // getter/setter/toString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String operation;
	private String method;
	private String params;
	private Long time;
	private String ip;
	private Date createdTime;
}

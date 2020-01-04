package cn.tedu.dao;

import cn.tedu.vo.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LogMapper extends BaseMapper<Log> {
    /**
     * 获取所有用户的信息
     *
     * @return 所有用户的信息
     */
    public List<Log> findPageObjects(@Param("username") String username,
                                     @Param("pageCurrent") Integer pageCurrent,
                                     @Param("startIndex") Integer startIndex,
                                     @Param("pageSize") Integer pageSize);
    /**
     * 查取页面中的总记录数
     *
     * @return
     */
    public int getRowCount();
}

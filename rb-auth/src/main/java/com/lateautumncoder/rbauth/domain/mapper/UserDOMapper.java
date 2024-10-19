package com.lateautumncoder.rbauth.domain.mapper;

import com.lateautumncoder.rbauth.domain.dataobject.UserDO;

/**
 * Created by 40973 ON 2024-10-19 1:57
 */
public interface UserDOMapper {
    /**
     * 根据主键 ID 查询
     *
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据主键 ID 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入记录
     *
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}

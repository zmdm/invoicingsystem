package com.bjfe.genuine.software.invoicingsystem.mapper.software;

/**
 * Created by Administrator on 2018/1/9.
 */

import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件类型数据持久接口
 * 编写人：宋超洋
 */
public interface ISoftTypeDao {
    /**
     * 添加软件类型
     * @param softTypeVO
     * @return
     * @throws SQLException
     */
    public int insertSoftType(SoftTypeVO softTypeVO)throws SQLException;

    /**
     * 查询显示软件类型
     * @return
     * @throws SQLException
     */
    public List<SoftTypeVO> selectSoftList()throws SQLException;

    /**
     * 查询软件类型数量
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectSoftTypeCount(Map whereMap)throws SQLException;

    /**
     * 修改软件类型
     * @return
     * @throws SQLException
     */
    public int updateSoftType(SoftTypeVO softTypeVO)throws SQLException;

    /**
     * 删除软件类型
     * @return
     * @throws SQLException
     */
    public int deleteSoftType(String []pk_softtype)throws SQLException;

    /**
     * 查询所有软件类型
     * @return
     * @throws SQLException
     */
    public List<SoftTypeVO> selectSoftTypeAll()throws SQLException;

    /**
     * 通过名称和编码查询软件类型数量
     * @return
     * @throws SQLException
     */
    public int selectSoftTypeCountBynameAndcode(@Param("name")String name)throws SQLException;

    /**
     * 查询软件类型数量
     * @return
     * @throws SQLException
     */
    public String selectSoftTypeCount()throws SQLException;
}

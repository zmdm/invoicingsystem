package com.bjfe.genuine.software.invoicingsystem.mapper.software;

/**
 * Created by Administrator on 2018/1/9.
 */

import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件类型数据持久接口
 * 编写人：宋超洋
 */
public interface ISoftInfoDao {
    /**
     * 添加软件信息
     * @param softInfoVO
     * @return
     * @throws SQLException
     */
    public int insertSoftInfo(SoftInfoVO softInfoVO)throws SQLException;

    /**
     * 查询显示软件信息
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public List<SoftInfoVO> selectSoftInfoList(Map whereMap)throws SQLException;

    /**
     * 查询软件信息数量
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectSoftInfoCount(Map whereMap)throws SQLException;

    /**
     * 修改软件信息
     * @return
     * @throws SQLException
     */
    public int updateSoftInfo(SoftInfoVO softInfoVO)throws SQLException;

    /**
     * 删除软件信息
     * @return
     * @throws SQLException
     */
    public int deleteSoftInfo(String[] pk_soft)throws SQLException;

    /**
     * 查询所有软件信息
     * @return
     * @throws SQLException
     */
    public List<SoftInfoVO> selectSoftInfoAll()throws SQLException;

    /**
     * 通过编码查询软件信息数量
     * @return
     * @throws SQLException
     */
    public int selectSoftInfoCountBycode(@Param("name") String name,@Param("version") String version)throws SQLException;

    /**
     * 通过软件类型查询软件信息数量
     * @param pk_softtype
     * @return
     * @throws SQLException
     */
    public Map selectSoftInfoCountByPksofttype(String pk_softtype)throws SQLException;

    /**
     * 通过名称查询软件信息的数量
     * @param name
     * @return
     * @throws SQLException
     */
    public int selectSoftInfoCountByName(String name)throws SQLException;

    /**
     * 通过主键查询软件信息
     * @param pk_soft
     * @return SoftInfoVO
     * @throws SQLException
     */
    public List<SoftInfoVO> selectSoftVoByPkSoft(String[] pk_soft)throws SQLException;
}

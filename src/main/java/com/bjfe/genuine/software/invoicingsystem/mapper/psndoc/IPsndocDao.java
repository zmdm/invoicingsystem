package com.bjfe.genuine.software.invoicingsystem.mapper.psndoc;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 人员数据持久接口
 * 编写人：宋超洋
 */
public interface IPsndocDao {
    /**
     * 查询显示人员信息
     * @return
     * @throws SQLException
     */
    public List<PsndocVO> selectPsndocList(Map map)throws SQLException;

    /**
     * 查询人员信息数量
     * @param map
     * @return
     * @throws SQLException
     */
    public int selectPsndocCount(Map map)throws SQLException;

    /**
     * 通过组织查询人员数量
     * @return
     * @throws SQLException
     */
    public int selectPsndocCountByPk_org(Map map)throws SQLException;

    /**
     * 添加人员信息
     * @param psndocVO
     * @return
     * @throws SQLException
     */
    public int insertPsndoc(PsndocVO psndocVO)throws SQLException;

    /**
     * 删除人员信息
     * @param pk_psndoc
     * @return
     * @throws SQLException
     */
    public int deletePsndoc(String[]pk_psndoc)throws SQLException;

    /**
     * 修改人员信息
     * @param psndocVO
     * @return
     * @throws SQLException
     */
    public int updatePsndoc(PsndocVO psndocVO)throws SQLException;

    /**
     * 修改人员信息状态
     * @param pk_psndoc
     * @param enablestate
     * @return
     * @throws SQLException
     */
    public int updatePsndocIstate(@Param("enablestate")int enablestate,@Param("pk_psndoc") String []pk_psndoc)throws SQLException;

    /**
     * 查询所有未删除的人员信息
     * @return
     * @throws SQLException
     */
    public List<PsndocVO> selectPsndocAll()throws SQLException;

    /**
     * 通过岗位查询相关人员信息数量
     * @param pk_job
     * @return
     * @throws SQLException
     */
    public Map selectPsndocCountByJob(String pk_job)throws SQLException;
}

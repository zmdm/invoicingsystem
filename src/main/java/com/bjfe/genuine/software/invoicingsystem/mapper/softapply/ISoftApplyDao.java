package com.bjfe.genuine.software.invoicingsystem.mapper.softapply;

/**
 * Created by Administrator on 2018/1/11.
 */

import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyBVO;
import com.bjfe.genuine.software.invoicingsystem.model.softapply.SoftApplyHVO;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 软件使用需求申请数据持久接口
 * 编写人：宋超洋
 */
public interface ISoftApplyDao {
    /**
     * 保存软件使用需求申请主表
     * @param softApplyHVO
     * @return
     * @throws SQLException
     */
    public int insertSoftApplyHVO(SoftApplyHVO softApplyHVO)throws SQLException;

    /**
     * 保存软件使用需求申请子表
     * @param list
     * @return
     * @throws SQLException
     */
    public int insertSoftApplyBVO(List<SoftApplyBVO> list)throws SQLException;

    /**
     * 条件查询软件使用需求申请
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public List<SoftApplyHVO> selectSoftApplyList(Map whereMap)throws SQLException;

    /**
     * 查询软件使用需求申请数量
     * @param whereMap
     * @return
     * @throws SQLException
     */
    public int selectSoftApplyCount(Map whereMap)throws SQLException;

    /**
     * 查询软件使用需求申请相关子表
     * @return
     * @throws SQLException
     */
    public List<SoftApplyBVO> selectSoftApplyBVOList()throws SQLException;
    /**
     * 修改软件使用需求申请字表信息
     * @param softApplyBVO
     * @return
     * @throws SQLException
     */
    public int updateSoftApply(SoftApplyBVO softApplyBVO)throws SQLException;

    /**
     * 删除软件需求申请表子表信息
     * @return
     * @throws SQLException
     */
    public int deleteSoftApplyB(List<String> list)throws SQLException;

    /**
     * 删除软件需求申请表主表信息
     * @return
     * @throws SQLException
     */
    public int deleteSoftApply(Map map)throws SQLException;

    /**
     * 修改软件申请表状态
     * @param softApplyHVO
     * @throws SQLException
     */
    public void updateSoftApplyBillstatus(SoftApplyHVO softApplyHVO)throws SQLException;

    /**
     * 查询正在审核的申请
     * @return
     * @throws SQLException
     */
    public List<SoftApplyHVO> selectSoftApplyIsApply(Map map)throws SQLException;

    /**
     * 通过软件申请主表字段修改申请状态
     * @param list
     * @throws SQLException
     */
    public void updateSoftApplyBVOBillstatusBypk_softapply(List<String> list)throws SQLException;

    /**
     * 通过软件申请主表主键查询子表数据
     * @return
     * @throws SQLException
     */
    public List<SoftApplyBVO> selectSoftApplyBVOBypk_soft_apply(@Param("pk_soft_apply") String pk_soft_apply)throws SQLException;

    /**
     * 通过申请主表主键查询申请部门
     * @param pk_soft_apply
     * @return
     * @throws SQLException
     */
    public String selectApplyDeptBypk_soft_apply(@Param("pk_soft_apply") String pk_soft_apply)throws SQLException;
}

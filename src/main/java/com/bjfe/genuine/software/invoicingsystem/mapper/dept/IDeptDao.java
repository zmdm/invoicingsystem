package com.bjfe.genuine.software.invoicingsystem.mapper.dept;

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 部门数据持久接口
 * 编写人：李宗泽
 */
public interface IDeptDao {
    /**
     * 添加部门
     * @return
     */
    public int insertDept(DeptVO deptVO)throws SQLException;

    /**
     * 查询显示部门
     * @return
     * @throws SQLException
     */
    public List<DeptVO> selectDeptList()throws SQLException;

    /**
     * 条件查询部门兼分页显示部门
     * @return
     * @throws SQLException
     */
    public List<DeptVO>selectDeptPage(Map map)throws SQLException;
    /**
     * 查询部门数量
     * @return
     * @throws SQLException
     */
    public int selectDeptCount(Map whereMap)throws SQLException;
    /**
     * 修改部门
     * @param deptVO
     * @return
     * @throws SQLException
     */
    public int updateDept(DeptVO deptVO)throws SQLException;

    /**
     * 删除部门
     * @param pk_deptdoc
     * @return
     * @throws SQLException
     */
    public int deleteDepts(String []pk_deptdoc)throws SQLException;

    /**
     * 查询对应组织下部门数量
     * @return
     * @throws SQLException
     */
    public Map selectDeptByOrgCount(String pk_org)throws SQLException;


}

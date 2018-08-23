package com.bjfe.genuine.software.invoicingsystem.service.dept;

import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/2.
 */
public interface IDeptService {
    /**
     * 添加部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    public Map insertDept(DeptVO deptVO)throws GlobalException;

    /**
     * redis查询所有部门
     * @return
     * @throws GlobalException
     */
    public Map selectDeptList(Map whereMap)throws GlobalException;

    /**
     * 条件查询部门
     * @return
     * @throws GlobalException
     */
    public Map selectDeptPage(Map WhereMap)throws GlobalException;
    /**
     * 修改部门
     * @param deptVO
     * @return
     * @throws GlobalException
     */
    public Map updateDept(DeptVO deptVO)throws GlobalException;

    /**
     * 删除部门
     * @return
     * @throws GlobalException
     */
    public Map deleteDept(String[]depts)throws GlobalException;
}

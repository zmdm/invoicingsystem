package com.bjfe.genuine.software.invoicingsystem.service.cuser;

import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;
import java.util.Map;
//业务层 server层 主要负责业务模块逻辑的设计  首先 设计接口 在设计实现类  接着在spring中配置文件中配置其实现的关联 就可以
//在Impl中调用接口进行业务逻辑处理 Impl中业务实现要调用以定义的Dao接口
/**
 * Created by Administrator on 2018-01-03.
 */
/**
 * 用户service
 * 编写人：李宗泽
 */
public interface ICuserService {
    /**
     * 添加用户
     * @param cuserVO
     * @return
     * @throws GlobalException
     */
    public Map insertCuser(CuserVO cuserVO)throws GlobalException;

    /**
     * 查询显示用户
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map selectCuserList(Map whereMap)throws GlobalException;

    /**
     * 删除用户
     * @param cuserid
     * @return
     * @throws GlobalException
     */
    public Map deleteCuser(String[]cuserid)throws GlobalException;

    /**
     * 修改用户
     * @param cuserVO
     * @return
     * @throws GlobalException
     */
    public Map updateCuser(CuserVO cuserVO)throws GlobalException;

    /**
     * 修改用户状态
     * @param whereMap
     * @return
     * @throws GlobalException
     */
    public Map updateCuserState(Map whereMap)throws GlobalException;

    /**
     * 查询所有用户角色信息是否分配
     * @return
     */
    public Map editUserNoRole(String pk_role)throws GlobalException;
    /**
     * 通过用户编码和密码判断用户
     * @param cuserVO
     * @return
     */
    public Map customerByCodeAndPass(CuserVO cuserVO)throws GlobalException;

    /**
     *查询本部门领导
     * @param map
     * @return
     * @throws GlobalException
     */
    public String selectDeptLeaderUser(Map map)throws GlobalException;

    /**
     * 查询信息化部门用户
     * @param map
     * @return
     * @throws GlobalException
     */
    public String selectInternationDeptUser(Map map)throws GlobalException;

    /**
     * 查询财务部门用户
     * @param map
     * @return
     * @throws GlobalException
     */
    public String selectFinanceDeptUser(Map map)throws GlobalException;

    /**
     * 查询单位领导用户
     * @param map
     * @return
     * @throws GlobalException
     */
    public String selectOrgLederUser(Map map)throws GlobalException;
}

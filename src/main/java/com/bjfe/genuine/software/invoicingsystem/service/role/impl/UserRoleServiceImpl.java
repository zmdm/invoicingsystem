package com.bjfe.genuine.software.invoicingsystem.service.role.impl;

/**
 * Created by Administrator on 2017/11/7.
 */


import com.bjfe.genuine.software.invoicingsystem.mapper.role.IUserRoleDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.userrole.UserRoleVO;
import com.bjfe.genuine.software.invoicingsystem.service.role.IUserRoleService;
import com.bjfe.genuine.software.invoicingsystem.util.pk.DateUtil;
import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关系业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("IUserRoleService")
public class UserRoleServiceImpl implements IUserRoleService {
    @Resource(name = "IUserRoleDao")
    private IUserRoleDao IUserRoleDao;

    /**
     * 管理用户角色关系
     * @param newUserRolebList
     * @return
     */
    public int editUserRole(List<UserRoleVO> newUserRolebList)throws GlobalException {
        int result = 0;
        try {
            for (int a =0;a<newUserRolebList.size();a++){
                //获取用户角色关系对象中的pk_role的值
                String pk_role=newUserRolebList.get(a).getPk_role();
                //通过角色主键查询相关的数据
                List<UserRoleVO> oldeUserRoleList = IUserRoleDao.selectUserRoleByConditon(pk_role);

                Map<String,UserRoleVO> oldUserRoleBMap=null;//存放用户主键，和用户相关主键的对象
                if(oldeUserRoleList!=null&&oldeUserRoleList.size()>0){
                    oldUserRoleBMap=new HashMap<String,UserRoleVO>();//存放用户主键，和用户相关主键的对象
                    for(int i=0;i<oldeUserRoleList.size();i++){
                        oldUserRoleBMap.put(oldeUserRoleList.get(i).getCuserid(),oldeUserRoleList.get(i));
                    }
                }
                for(int i=0;i<newUserRolebList.size();i++){
                    UserRoleVO newUserRoleBVO=newUserRolebList.get(i);
                    //判断
                    if(oldUserRoleBMap!=null&&oldUserRoleBMap.containsKey(newUserRoleBVO.getCuserid())){//2边有
                        if (oldUserRoleBMap.get(newUserRoleBVO.getCuserid()).getDr() ==1){
                            IUserRoleDao.updateUserRoleDr(oldUserRoleBMap.get(newUserRoleBVO.getCuserid()).getCuserid(),oldUserRoleBMap.get(newUserRoleBVO.getCuserid()).getPk_role());
                            oldUserRoleBMap.remove(newUserRoleBVO.getCuserid());
                        }else {
                            oldUserRoleBMap.remove(newUserRoleBVO.getCuserid());
                        }
                    }else {//需要新增的
                        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                        String pk_apply = new StringBuffer(ConstantVO.USER_ROLE).append(Long.toString(worker.nextId())).toString();
                        newUserRoleBVO.setPk_user_role(pk_apply);
                        IUserRoleDao.insertUserRole(newUserRoleBVO);
                    }
                }
                //剩下的oldUserRoleBMap需要删除的
                if (oldUserRoleBMap!=null){
                    List<String> pkUserList = new ArrayList<>();
                    pkUserList.addAll(oldUserRoleBMap.keySet());
                    for (String str :pkUserList){
                        System.out.println(str);
                    }
                    if (pkUserList.size()>0){
                        IUserRoleDao.deleteUserRole(pkUserList,pk_role);
                    }
                }
                result++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return result;
    }
}

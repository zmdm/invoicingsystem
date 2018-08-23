package com.bjfe.genuine.software.invoicingsystem.service.role.impl;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.role.IRolePowerDao;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.model.rolepermission.RolePermissionVO;
import com.bjfe.genuine.software.invoicingsystem.service.role.IRolePowerService;
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
 * 角色权限关系业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("IRolePowerService")
public class RolePowerServiceImpl implements IRolePowerService {
    @Resource(name = "IRolePowerDao")
    private IRolePowerDao IRolePowerDao;
    @Resource(name = "IOrgDao")
    private IOrgDao IOrgDao;

    /**
     * 编辑角色权限关系
     * @param newRolePowerList
     * @return
     */
    public int editRolePower(List<RolePermissionVO> newRolePowerList)throws GlobalException {
        int result = 0;
        try {
            for (int a =0;a<newRolePowerList.size();a++){
//                遍历角色权限关系类型的 newRolePowerList数据的大小
                String pk_role = newRolePowerList.get(a).getPk_role();
//               的到每一个角色权限关系表里面的角色角色主键
                List<RolePermissionVO> oldRolePowerList = IRolePowerDao.selectRolePowerByCondition(pk_role);
//                通过角色主键查询角色权限关系
                Map<String,RolePermissionVO> oldRolePowerMap = null;
//                创建一个存放角色权限关系的空的map
                if (oldRolePowerList!=null && oldRolePowerList.size()>0){
//                如果权限关系不为空并且大小>0
                    oldRolePowerMap = new HashMap<>();
//                  空的map开辟新的空间 创建了一个对象
                    for (int i =0;i<oldRolePowerList.size();i++){
//                        遍历角色权限关系大小
                        oldRolePowerMap.put(oldRolePowerList.get(i).getPk_permid(),oldRolePowerList.get(i));
//                      把权限主键和值塞到空的map容器里面
                    }
                }
                for(int i=0;i<newRolePowerList.size();i++){
//                    遍历权限关系原始数据的大小
                    RolePermissionVO newRolePowerBVO=newRolePowerList.get(i);
//                    得到每一个权限关系的原始数据 存放到newRolePowerBVO中
                    //判断
                    if(oldRolePowerMap!=null&&oldRolePowerMap.containsKey(newRolePowerBVO.getPk_permid())){//2边有
//                       如果权限主键和值的map不为空 并且和原始权限关系表数据 比较权限主键和值数据里面有没有权限主键这个key
                        if (oldRolePowerMap.get(newRolePowerBVO.getPk_permid()).getDr()==1){
//                          通过 权限主键 获得oldRolePowerMap里面的dr值
                            IRolePowerDao.updateRolePowerDr(oldRolePowerMap.get(newRolePowerBVO.getPk_permid()).getPk_role(),oldRolePowerMap.get(newRolePowerBVO.getPk_permid()).getPk_permid());
//                          修改表中已有角色权限dr字段是通过角色主键 和权限主键来更新
                            oldRolePowerMap.remove(newRolePowerBVO.getPk_permid());
//                           删除原始数据里面的权限主键
                        }else {
                            oldRolePowerMap.remove(newRolePowerBVO.getPk_permid());
//                            如果不满足就删除  权限主键
                        }
                    }else {//需要新增的
                        //String pk_org = IOrgDao.selectOrgPk();
                        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
//                        主键 生成的策略
                        String pk_apply = new StringBuffer(ConstantVO.ROLE_PERMISSION).append(Long.toString(worker.nextId())).toString();
//                        前两位长度+后十八位的长度
                        newRolePowerBVO.setPk_role_power(pk_apply);
//                          设置角色权限主键的大小长度
                        newRolePowerBVO.setTs(DateUtil.getTime());
//                      设置时间戳的当前时间
                        IRolePowerDao.insertRolePower(newRolePowerBVO);
//                        添加角色权限关系
                    }
                }
                //剩下的oldUserRoleBMap需要删除的
                if (oldRolePowerMap!=null){
                    List<String> pkPowerList = new ArrayList<>();
                    pkPowerList.addAll(oldRolePowerMap.keySet());
                    if (pkPowerList.size()>0){
                        IRolePowerDao.deleteRolePower(pkPowerList,pk_role);
                    }
                }
                result++;
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return result;
    }
}

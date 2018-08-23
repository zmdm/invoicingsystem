package com.bjfe.genuine.software.invoicingsystem.service.qxgl.impl;

/**
 * Created by Administrator on 2017/11/10.
 */

import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.qxgl.IPermissionMenuDao;
import com.bjfe.genuine.software.invoicingsystem.model.permissionmenu.PermissionMenuVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
import com.bjfe.genuine.software.invoicingsystem.service.qxgl.IPermissionMenurService;
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
 * 权限菜单关系业务逻辑实现类
 * 编写人：宋超洋
 */
@Service("IPermissionMenurService")
public class PermissionMenuServiceImpl implements IPermissionMenurService {
    @Resource(name = "IPermissionMenuDao")
    private IPermissionMenuDao IPermissionMenuDao;
    @Resource(name ="IOrgDao" )
    private IOrgDao IOrgDao;

    /**
     * 编辑权限菜单关系
     * @param newPowerMenuList
     * @return
     */
    public int editPowerMenu(List<PermissionMenuVO> newPowerMenuList)throws GlobalException{
        int result = 0;
        SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
        try {
                String pk_power = newPowerMenuList.get(1).getPk_permid();
                List<PermissionMenuVO> oldPowerMenuList = IPermissionMenuDao.selectPowerMenuByCondition(pk_power);
                Map<String,PermissionMenuVO> oldPowerMenuMap = null;
                if (oldPowerMenuList!=null && oldPowerMenuList.size()>0){
                    oldPowerMenuMap = new HashMap<>();
                    for (int i =0;i<oldPowerMenuList.size();i++){
                        oldPowerMenuMap.put(oldPowerMenuList.get(i).getCfunid(),oldPowerMenuList.get(i));
                    }
                }
                for(int i=0;i<newPowerMenuList.size();i++){
                    PermissionMenuVO powerMenuVO=newPowerMenuList.get(i);
                    //判断
                    if(oldPowerMenuMap!=null&&oldPowerMenuMap.containsKey(powerMenuVO.getCfunid())){//2边有
                        if (oldPowerMenuMap.get(powerMenuVO.getCfunid()).getDr() ==1){
                            IPermissionMenuDao.updatePowerMenuDr(oldPowerMenuMap.get(powerMenuVO.getCfunid()).getPk_permid(),oldPowerMenuMap.get(powerMenuVO.getCfunid()).getCfunid());
                            oldPowerMenuMap.remove(powerMenuVO.getCfunid());
                        }else {
                            oldPowerMenuMap.remove(powerMenuVO.getCfunid());
                        }
                    }else {//需要新增的
                        //String pk_org = IOrgDao.selectOrgPk();
                        String pk_apply = new StringBuffer(ConstantVO.PERMISSION_MENU).append(Long.toString(worker.nextId())).toString();
                        powerMenuVO.setPk_permid_cfunid(pk_apply);
                        //powerMenuVO.setPk_org(pk_org);
                        powerMenuVO.setTs(DateUtil.getTime());
                        IPermissionMenuDao.insertPowerMenu(powerMenuVO);
                    }
                }
                //剩下的oldUserRoleBMap需要删除的
                if (oldPowerMenuMap!=null){
                    List<String> pkMenuList = new ArrayList<>();
                    pkMenuList.addAll(oldPowerMenuMap.keySet());
                    if (pkMenuList.size()>0){
                        IPermissionMenuDao.deletePowerMenu(pkMenuList,pk_power);
                    }
                }
                result++;
        }catch (SQLException e){
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return result;
    }
}

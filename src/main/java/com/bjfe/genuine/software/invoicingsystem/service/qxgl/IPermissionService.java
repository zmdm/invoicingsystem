package com.bjfe.genuine.software.invoicingsystem.service.qxgl;

import com.bjfe.genuine.software.invoicingsystem.model.qxgl.RightsManVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.Map;
/**
 * Created by Administrator on 2018-01-04.
 */
/**
 * 权限service
 * 编写人：李宗泽
 */
public interface IPermissionService {
        /**
         * 添加权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        public Map insertRightsMan(RightsManVO rightsManVO)throws GlobalException;

        /**
         * 查询显示权限
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        public Map selectRightsManList(Map whereMap)throws GlobalException;

        /**
         * 删除权限
         * @param pk_permid
         * @return
         * @throws GlobalException
         */
        public Map deleteRightsMan(String[]pk_permid)throws GlobalException;

        /**
         * 修改权限
         * @param rightsManVO
         * @return
         * @throws GlobalException
         */
        public Map updateRightsMan(RightsManVO rightsManVO)throws GlobalException;

        /**
         * 查询所有角色权限信息是否分配
         * @return
         * @throws GlobalException
         */
        public Map editPowerNoRole(String pk_role)throws GlobalException;
    }



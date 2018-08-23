package com.bjfe.genuine.software.invoicingsystem.service.org;

/**
 * Created by Administrator on 2018/1/2.
 */

import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;

import java.util.List;
import java.util.Map;

/**
 * 组织业务逻辑接口
 * 编写人：宋超洋
 */
public interface IOrgService {
    /**
     * 添加组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    public Map insertOrg(OrgVO orgVO)throws GlobalException;

    /**
     * redis查询所有组织
     * @return
     * @throws GlobalException
     */
    public Map selectOrgList(Map map)throws GlobalException;

    /**
     * 修改组织
     * @param orgVO
     * @return
     * @throws GlobalException
     */
    public Map updateOrg(OrgVO orgVO)throws GlobalException;

    /**
     * 删除组织
     * @return
     * @throws GlobalException
     */
    public Map deleteOrg(String[]orgs)throws GlobalException;

    /**
     * 条件查询组织
     * @return
     * @throws GlobalException
     */
    public Map selectOrgPage(Map whereMap)throws GlobalException;
}

package com.bjfe.genuine.software.invoicingsystem.service.cuser.impl;

/**
 * Created by Administrator on 2018-01-03.
 */

    import com.bjfe.genuine.software.invoicingsystem.mapper.cuser.ICuserDao;
    import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
    import com.bjfe.genuine.software.invoicingsystem.mapper.menu.IMenuDao;
    import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
    import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
    import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
    import com.bjfe.genuine.software.invoicingsystem.model.cuser.CuserVO;
    import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
    import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
    import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
    import com.bjfe.genuine.software.invoicingsystem.model.pub.constant.ConstantVO;
    import com.bjfe.genuine.software.invoicingsystem.model.pub.exception.GlobalException;
    import com.bjfe.genuine.software.invoicingsystem.service.cuser.ICuserService;
    import com.bjfe.genuine.software.invoicingsystem.service.psndoc.IPsndocService;
    import com.bjfe.genuine.software.invoicingsystem.util.paging.PagingUtilVO;
    import com.bjfe.genuine.software.invoicingsystem.util.pk.SnowflakeIdWorker;
    import com.bjfe.genuine.software.invoicingsystem.util.redis.RedisUtil;
    import org.apache.commons.lang3.StringUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.core.ValueOperations;
    import org.springframework.stereotype.Service;

    import javax.annotation.Resource;
    import java.io.Serializable;
    import java.sql.SQLException;
    import java.util.*;

/**
     * 用户业务逻辑实现类
     * 编写人：李宗泽
     */
    @Service("iCuserService")

    public class ICuserServiceImpl implements ICuserService{
        @Autowired
        private RedisTemplate<String,Serializable> redisTemplate;
        @Resource(name = "ICuserDao")
        private ICuserDao ICuserDao;
        @Resource(name = "IOrgDao")
        private IOrgDao iOrgDao;
        @Resource(name = "IDeptDao")
        private IDeptDao iDeptDao;
        @Resource(name = "IPsndocDao")
        private IPsndocDao iPsndocDao;
        @Resource(name = "IMenuDao")
        private IMenuDao iMenuDao;


        /**
         * 添加用户
         * @param cuserVO
         * @return
         * @throws GlobalException
         */
        @Override
        public Map insertCuser(CuserVO cuserVO) throws GlobalException {
            Map map = new HashMap<>();
            try {
                int count = ICuserDao.selectPsndocByCode(cuserVO.getUsername(),cuserVO.getUsercode());
                if (count ==0){
                    SnowflakeIdWorker worker = new SnowflakeIdWorker(9, 9);
                    String pk_apply = new StringBuffer(ConstantVO.CUSERID).append(Long.toString(worker.nextId())).toString();
                    cuserVO.setCuserid(pk_apply);
                    ICuserDao.insertCuser(cuserVO);
                    map.put("cuserid",pk_apply);
                    map.put("success",true);
                }else {
                    map.put("success",false);
                    map.put("info","用户或编码重复，请重新输入！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 查询显示用户
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        @Override
        public Map selectCuserList(Map whereMap) throws GlobalException {
            Map dataMap = null;
            ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
            List<OrgVO> orgList = (ArrayList<OrgVO>)valueOperations.get("orglist");
            List<DeptVO> deptList = (ArrayList<DeptVO>)valueOperations.get("deptlist");
            List<PsndocVO> personList = (ArrayList<PsndocVO>)valueOperations.get("psndoclist");
            try {
                int total = ICuserDao.selectCuserCount(whereMap);
                List<CuserVO> dataList = ICuserDao.selectCuserList(PagingUtilVO.pagingContain(whereMap,total));
                dataMap = new HashMap<>();
                if (dataList!=null){
                    orgList = RedisUtil.handleLis(null,deptList,orgList);
                    dataMap.put("pagesize",PagingUtilVO.pagingContain(whereMap,total).get("pageSize"));
                    dataMap.put("total",total);
                    dataMap.put("datalist",dataList);
                    dataMap.put("orglist",orgList);
                    dataMap.put("psndoc",personList);
                    dataMap.put("success",true);
                }else {
                    dataMap.put("success",false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return dataMap;
        }

        /**
         * 删除用户
         * @param cuserid
         * @return
         * @throws GlobalException
         */
        @Override
        public Map deleteCuser(String[] cuserid) throws GlobalException {
            Map map = new HashMap<>();
            try {
                int result = ICuserDao.deleteCuser(cuserid);
                if (result >0){
                    map.put("success",true);
                }else {
                    map.put("success",false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 修改用户
         * @param cuserVO
         * @return
         * @throws GlobalException
         */
        @Override
        public Map updateCuser(CuserVO cuserVO) throws GlobalException {
            Map map = new HashMap<>();
            try {
                if (cuserVO.getPassword()!=null && cuserVO.getNewpass() !=null){
                    String oldPass = ICuserDao.selectOldPass(cuserVO.getCuserid());
                    if (!oldPass.equals(cuserVO.getPassword())){
                        map.put("success",false);
                        map.put("info","原始密码错误，请重新输入！");
                        return map;
                    }
                }
                int result = ICuserDao.updateCuser(cuserVO);
                if (result ==1){
                    map.put("success",true);
                }else {
                    map.put("success",false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 修改用户状态
         * @param whereMap
         * @return
         * @throws GlobalException
         */
        @Override
        public Map updateCuserState(Map whereMap) throws GlobalException {
            Map map = new HashMap<>();
            String pk = (String) whereMap.get("cuserid");
            String []pks = pk.split(",");
            try {
                int result = ICuserDao.updatePsndocIstate((Integer) whereMap.get("enablestate"),pks);
                if (result >0){
                    map.put("success",true);
                }else {
                    map.put("success",false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 查询所有用户角色信息是否分配
         * @param pk_role
         * @return
         * @throws GlobalException
         */
        @Override
        public Map editUserNoRole(String pk_role) throws GlobalException {
            Map map = new HashMap<>();
            try {
                List<CuserVO> userList = ICuserDao.editUserNoRole(pk_role);
                if (userList!=null){
                    map.put("success",true);
                    map.put("userlist",userList);
                }else {
                    map.put("success",false);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
            return map;
        }

        /**
         * 用户登陆判断
         * @param cuserVO
         * @return
         */
        public Map customerByCodeAndPass(CuserVO cuserVO)throws GlobalException{
            Map map = new HashMap<>();
            try {
                    CuserVO userTemp = ICuserDao.customerByCodeAndPass(cuserVO);
                    if (userTemp!=null && 0 == userTemp.getEnablestate() && 0 == userTemp.getDr()){
                        List<MenuItemVO> menuItemList = iMenuDao.selectMenuByuser(cuserVO.getUsername());
                        List<MenuItemVO> listTemp1 = new ArrayList<>();
                        PagingUtilVO.sortStringMethod(menuItemList);
                        if (menuItemList.size()>0) {
                            for (int i = 0; i < menuItemList.size(); i++) {
                                MenuItemVO menuItem1 = menuItemList.get(i);
                                List<MenuItemVO> listTemp = new ArrayList<>();
                                if ("".equals(menuItem1.getPk_father())) {
                                    for (int b = 0; b < menuItemList.size(); b++) {
                                        MenuItemVO menuItem2 = menuItemList.get(b);
                                        if (menuItem1.getFuncode().equals(menuItem2.getPk_father())) {
                                            listTemp.add(menuItem2);
                                        }
                                    }
                                }
                                menuItem1.setMenuItemVOList(listTemp);
                                if (menuItem1.getMenuItemVOList().size() != 0) {
                                    listTemp1.add(menuItem1);
                                }
                            }
                        }
                        map.put("sessioncustomer",userTemp);
                        map.put("datalist",listTemp1);
                        map.put("success", true);
                    }else if (userTemp !=null && userTemp.getEnablestate()==1){
                        map.put("info","账号已被禁用，请联系管理员！");
                        map.put("success",false);
                    }else if (userTemp != null && userTemp.getDr() ==1){
                        map.put("info","账号不存在请联系管理员，请联系管理员！");
                        map.put("success",false);
                    }else {
                        map.put("info","账号或密码错误，请重新输入！");
                        map.put("success",false);
                    }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return map;

        }

    /**
     * 查询本部门领导
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public String selectDeptLeaderUser(Map map) throws GlobalException {
        String username;
        try {
            username = ICuserDao.selectDeptLeaderUser(map);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return username;
    }

    /**
     * 查询信息化部门用户
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public String selectInternationDeptUser(Map map) throws GlobalException {
        String username;
        try {
            List<String> list = ICuserDao.selectInternationDeptUser(map);
            username = StringUtils.join(list,",");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return username;
    }

    /**
     * 查询财务部门用户
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public String selectFinanceDeptUser(Map map) throws GlobalException {
        String username;
        try {
            List<String> list = ICuserDao.selectFinanceDeptUser(map);
            username = StringUtils.join(list,",");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return username;
    }

    /**
     * 查询单位领导用户
     * @param map
     * @return
     * @throws GlobalException
     */
    @Override
    public String selectOrgLederUser(Map map) throws GlobalException {
        String username;
        try {
            List<String> list = ICuserDao.selectOrgLederUser(map);
            username = StringUtils.join(list,",");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return username;
    }
}



package com.bjfe.genuine.software.invoicingsystem.util.parma;

import com.bjfe.genuine.software.invoicingsystem.mapper.computer.IComputerDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.dept.IDeptDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.job.IJobDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.menu.IMenuDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.org.IOrgDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.psndoc.IPsndocDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftInfoDao;
import com.bjfe.genuine.software.invoicingsystem.mapper.software.ISoftTypeDao;
import com.bjfe.genuine.software.invoicingsystem.model.cdl.MenuItemVO;
import com.bjfe.genuine.software.invoicingsystem.model.computer.ComputerVO;
import com.bjfe.genuine.software.invoicingsystem.model.dept.DeptVO;
import com.bjfe.genuine.software.invoicingsystem.model.job.JobVO;
import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import com.bjfe.genuine.software.invoicingsystem.model.psndoc.PsndocVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjlx.SoftTypeVO;
import com.bjfe.genuine.software.invoicingsystem.model.rjxx.SoftInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2018/2/27.
 */
@Component
public class ParamUtil {
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Autowired
    private IOrgDao iOrgDao;
    @Autowired
    private IDeptDao iDeptDao;
    @Autowired
    private IJobDao iJobDao;
    @Autowired
    private IPsndocDao iPsndocDao;
    @Autowired
    private IMenuDao iMenuDao;
    @Autowired
    private IComputerDao iComputerDao;
    @Autowired
    private ISoftInfoDao iSoftInfoDao;
    @Autowired
    private ISoftTypeDao iSoftTypeDao;


    /**
     * 初始化组织数据
     */
    @PostConstruct
    public void initOrgParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("orglist", null);
        List<OrgVO> orgList= null;
        try {
            orgList = iOrgDao.selectOrgList();
            valueOperations.set("orglist", (Serializable) orgList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化部门数据
     */
    @PostConstruct
    public void initDeptParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("deptlist", null);
        List<DeptVO> deptlist = null;
        try {
            deptlist = iDeptDao.selectDeptList();
            valueOperations.set("deptlist", (Serializable) deptlist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化岗位数据
     */
    @PostConstruct
    public void initJobParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("joblist",null);
        List<JobVO> joblist = null;
        try {
            joblist = iJobDao.selectJobList();
            valueOperations.set("joblist", (Serializable)joblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化人员信息数据
     */
    @PostConstruct
    public void  initPersonParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("psndoclist",null);
        List<PsndocVO> list = null;
        try {
            list = iPsndocDao.selectPsndocAll();
            valueOperations.set("psndoclist", (Serializable) list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化菜单项数据
     */
    @PostConstruct
    public void initMenuParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("menulist",null);
        List<MenuItemVO> menulist = null;
        try {
            menulist = iMenuDao.selectMenuAll();
            valueOperations.set("menulist", (Serializable) menulist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化计算机信息数据
     */
    @PostConstruct
    public void initComputerParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("computerlist",null);
        List<ComputerVO> computerList = null;
        try {
            computerList = iComputerDao.selectComputerList();
            valueOperations.set("computerlist", (Serializable) computerList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化软件信息数据
     */
    @PostConstruct
    public void initSoftInfoParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("softinfolist",null);
        List<SoftInfoVO> list = null;
        try {
            list =iSoftInfoDao.selectSoftInfoAll();
            valueOperations.set("softinfolist", (Serializable) list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从初始化软件类型数据
     */
    @PostConstruct
    public void initSoftTypeParam(){
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("softtypelist",null);
        List<SoftTypeVO> list = null;
        try {
            list =iSoftTypeDao.selectSoftTypeAll();
            valueOperations.set("softtypelist", (Serializable) list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

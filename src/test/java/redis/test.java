package redis;

import com.bjfe.genuine.software.invoicingsystem.model.org.OrgVO;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/29.
 */
public class test {
    public static void main(String[]args){
       /* ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/application-redis.xml");
        RedisTemplate<String, Serializable> redisTemplate = (RedisTemplate<String, Serializable>)context.getBean("redisTemplate");
        ValueOperations<String, Serializable> valueOperations = redisTemplate.opsForValue();
        OrgVO orgVO = new OrgVO();
        orgVO.setPk_fatherorg("fdafdafdafdasf");
        orgVO.setCode("dfafdafdafda");

        ArrayList<OrgVO> userList = new ArrayList<OrgVO>();
        userList.add(orgVO);
        userList.add(orgVO);
        valueOperations.set("userListKey", userList);*/

       /* List<Serializable> userList2 = new ArrayList<Serializable>();
        userList2.add(orgVO);
        userList2.add(orgVO);
        ListOperations<String, Serializable> listOperations = redisTemplate.opsForList();
        listOperations.leftPushAll("userList2", userList2);
*/
        // 引擎配置
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("spring/application-activiti.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine=pec.buildProcessEngine();

    }

}

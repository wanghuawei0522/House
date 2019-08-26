package com.team.house;

import com.team.house.service.DistrictService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 王建兵
 * @Classname Test
 * @Description TODO
 * @Date 2019/8/12 9:58
 * @Created by Administrator
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
        DistrictService districtService=(DistrictService) ctx.getBean("districtServiceImpl");
        districtService.delDistrict(1017);
    }
}

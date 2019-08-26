package com.team.house.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.mapper.HouseMapper;
import com.team.house.service.HouseService;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王建兵
 * @Classname HouseServiceImpl
 * @Description TODO
 * @Date 2019/8/16 10:41
 * @Created by Administrator
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;


    public int addHouse(House house) {
        return houseMapper.insertSelective(house);
    }


    public PageInfo<House> getHouseByUser(Page page, Integer userid) {
        //给page的rows属性设置默认值:后期可能不传页大小
        PageHelper.startPage(page.getPage(),page.getRows());
        List<House> list=houseMapper.getHouseByUser(userid);
        return new PageInfo<House>(list);
    }


    public House getHouse(String id) {
        return houseMapper.getHouseById(id);
    }


    public int updateHouse(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    public int delHouseState(String id, Integer state) {
        House house = new House();
        house.setId(id);
        house.setIsdel(state);
        return houseMapper.updateByPrimaryKeySelective(house);

    }

    public PageInfo<House> getHouseByIsPass(Page page, Integer state) {
        //开启分页
        PageHelper.startPage(page.getPage(),page.getRows());
        //查询所有
        List<House>list = houseMapper.getHouseByIsPass(state);

        return new PageInfo<House>(list);
    }

    public int PassHouse(String id, Integer state) {
        House house=new House();
        //设置id
        house.setId(id);
        //设置审核状态
        house.setIspass(state);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    public PageInfo<House> getHouseBySearch(HouseCondition condition) {
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> list = houseMapper.getHouseBySearch(condition);
        return new PageInfo<House>(list);
    }
}

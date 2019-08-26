package com.team.house.service;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;

/**
 * @author 王建兵
 * @Classname HouseService
 * @Description TODO
 * @Date 2019/8/16 10:41
 * @Created by Administrator
 */
public interface HouseService {
    //发布出租房信息
    public int addHouse(House house);

    /**
     * 查询用户发布的出租房
     * @param page  分页
     * @param userid 用户编号
     * @return 出租房信息
     */
    public PageInfo<House> getHouseByUser(Page page, Integer userid);

    /**
     * 查询单条出租房信息
     * @param id
     * @return
     */
    public House getHouse(String id);

    /**
     * 修改出租房信息
     * @param house 实体
     * @return 影响行数
     */
    public int updateHouse(House house);
    //删除单条
    public int delHouseState(String id,Integer state);
    //通过审核状态查询出租房的信息
    public PageInfo<House> getHouseByIsPass(Page page,Integer state);
    //修改出租房的审核状态
    public int PassHouse(String id,Integer state);

    /**
     * 浏览出租房信息
     * @param condition  查询条件
     * @return  所有出租房
     */
    public PageInfo<House> getHouseBySearch(HouseCondition condition);
}

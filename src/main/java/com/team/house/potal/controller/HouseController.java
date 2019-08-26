package com.team.house.potal.controller;

import com.github.pagehelper.PageInfo;
import com.team.house.entity.House;
import com.team.house.entity.Users;
import com.team.house.service.HouseService;
import com.team.house.util.HouseCondition;
import com.team.house.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author 王建兵
 * @Classname HouseController
 * @Description TODO
 * @Date 2019/8/16 10:45
 * @Created by Administrator
 */
@Controller
@RequestMapping("/page")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/addHouse")
    public String addHouse(House house, @RequestParam(value="pfile",required = false) CommonsMultipartFile pfile, HttpSession session, Model model){
        //一个CommonsMultipartFile类的对象就代表一个表单文件域，一张图片
        //获取上传文件的信息
       /* System.out.println("文件名"+pfile.getOriginalFilename());
        System.out.println("文件大小:"+pfile.getSize());
        System.out.println("文件类型:"+pfile.getContentType());*/
       try {
           //第一步上传图片
           String path="d:\\images\\";  //存放文件的位置
           //生成唯一文件名
           String oldName=pfile.getOriginalFilename();
           String expname=oldName.substring(oldName.lastIndexOf("."));
           String filename=System.currentTimeMillis()+expname;
           File file=new File(path+filename);
           pfile.transferTo(file);  //上传，保存

           //第二步保存信息到数据库
           //设置主键
           house.setId(System.currentTimeMillis()+"");
           //设置发布出租房的用户
           Users users=(Users) session.getAttribute("userInfo");
           house.setUserId(users.getId());
           //设置图片
           house.setPath(filename);
           houseService.addHouse(house);
           return "guanli";  //跳转到管理页
       }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("info","上传文件失败..");
       }
        return "redirect:getHouse";
    }


    //查询用户出租房
    @RequestMapping("/getHouse")
    public String getHouse(Page page, HttpSession session, Model model){  //page只为接收页码
        //获取用户id
        Users user=(Users)session.getAttribute("userInfo");
        //调用业务查询出租房
        PageInfo<House> pageInfo=houseService.getHouseByUser(page,user.getId());
        //将结果填充至MODEL
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    //显示单条出租房信息
    @RequestMapping("/getSingleHouse")
    public String getSingleHouse(String id,Model model){  //page只为接收页码
        //获取出租房信息
        House house=houseService.getHouse(id);
        //将信息填充到作用域
        model.addAttribute("h",house);
        return "showHouse";
    }


    //显示单条出租房信息
    @RequestMapping("/updateHouse")
    public String updateHouse(String delimage,House house,@RequestParam("pfile") CommonsMultipartFile pfile){  //page只为接收页码
       try {
           //判断用户是否选择文件
           String oldName=pfile.getOriginalFilename();
           if(oldName.equals(""))  //没有选择文件
           {
               houseService.updateHouse(house);
           }else
           {
               //上传
               //第一步上传图片
               String path="d:\\images\\";  //存放文件的位置
               //生成唯一文件名
               String expname=oldName.substring(oldName.lastIndexOf("."));
               String filename=System.currentTimeMillis()+expname;
               File file=new File(path+filename);
               pfile.transferTo(file);  //上传，保存
               //更新数据库
               //设置更新图片路径
               house.setPath(filename);
               houseService.updateHouse(house);
               //删除旧图
               File delfile=new File(path+delimage);
               delfile.delete();
           }
           return "redirect:getHouse";
       }catch (Exception e){
           e.printStackTrace();
       }
       return "error";
    }

    @RequestMapping("/delHouse")
    public String delHouse(String id){
        int temp = houseService.delHouseState(id,1);
        if (temp>0)
            return  "redirect:getHouse";
        else
            return "error";
    }

    @RequestMapping("/searchHouse")
    public String searchHouse(HouseCondition condition, Model model){
        PageInfo<House> pageInfo = houseService.getHouseBySearch(condition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        return "list";
    }

}

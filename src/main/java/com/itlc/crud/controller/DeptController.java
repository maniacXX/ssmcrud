package com.itlc.crud.controller;

import com.itlc.crud.bean.Dept;
import com.itlc.crud.bean.Msg;
import com.itlc.crud.service.inter.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/6/19 20:55
 */

@Controller
@RequestMapping("/department")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     *
     * @return 所有部门的信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        //查询所有部门信息
        List<Dept> list=deptService.getDepts();
        return Msg.success().add("depts",list);
    }
}

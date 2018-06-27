package com.itlc.crud.service.impl;

import com.itlc.crud.bean.Dept;
import com.itlc.crud.dao.DeptMapper;
import com.itlc.crud.service.inter.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/6/19 20:58
 */

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    private DeptMapper deptMapper;

    /**
     *
     * @return 所有部门信息
     */
    @Override
    public List<Dept> getDepts() {
        return deptMapper.selectByExample(null);
    }
}

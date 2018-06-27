package com.itlc.crud.service.inter;

import com.itlc.crud.bean.Dept;

import java.util.List;

public interface DeptService {
    /**
     *
     * @return 所有员工信息
     */
    public List<Dept> getDepts();
}

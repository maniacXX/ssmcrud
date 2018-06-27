package com.itlc.crud.service.impl;

import com.itlc.crud.bean.Employee;
import com.itlc.crud.bean.EmployeeExample;
import com.itlc.crud.dao.EmployeeMapper;
import com.itlc.crud.service.inter.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/6/7 11:03
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 查询所有员工
     * @return 员工集合
     */
    @Override
    public List<Employee> getAll(){
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     *
     * @param employee 要保存的员工对象
     */
    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 用户名查重
     * @param lastName 要检查的用户名
     * @return 检查结果 true表示可用
     */
    @Override
    public boolean checkUser(String lastName) {
        EmployeeExample employeeExample=new EmployeeExample();
        EmployeeExample.Criteria criteria=employeeExample.createCriteria();
        criteria.andLastNameEqualTo(lastName);
        long t=employeeMapper.countByExample(employeeExample);
        return t==0;
    }

    /**
     * 查询员工信息
     * @param id 员工id
     * @return 员工信息
     */
    @Override
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新员工信息
     * @param employee 要更新的员工信息
     */
    @Override
    public void update(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 通过id1删除单个员工
     * @param id 要删除员工的id
     */
    @Override
    public void deleteEmpById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除多个员工
     * @param ids 用-分隔的多个id号
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample employeeExample=new EmployeeExample();
        EmployeeExample.Criteria criteria=employeeExample.createCriteria();
        criteria.andIdIn(ids);
        employeeMapper.deleteByExample(employeeExample);
    }
}

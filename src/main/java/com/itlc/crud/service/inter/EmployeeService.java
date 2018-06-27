package com.itlc.crud.service.inter;

import com.itlc.crud.bean.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 查询所有员工
     * @return 员工集合
     */
    public List<Employee> getAll();

    /**
     * 保存员工的方法
     * @param employee 要保存的员工对象
     */
    void saveEmp(Employee employee);

    /**
     * 用户名查重
     * @param lastName 要检查的用户名
     * @return 查询结果 true表示可用
     */
    boolean checkUser(String lastName);

    /**
     * 通过id获取员工的信息
     * @param id 员工id
     * @return 员工信息
     */
    Employee getEmp(Integer id);

    /**
     * 员工更新的方法
     * @param employee 要更新的员工信息
     */
    void update(Employee employee);

    /**
     * 通过id删除单个员工
     * @param id
     */
    void deleteEmpById(Integer id);

    /**
     * 批量删除多个员工
     * @param ids 用-分隔的多个id号
     */
    void deleteBatch(List<Integer> ids);
}

package com.itlc.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itlc.crud.bean.Employee;
import com.itlc.crud.bean.Msg;
import com.itlc.crud.service.inter.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理员工的CRUD请求
 *
 * @author Duan
 * @date 2018/6/7 10:49
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController{

    @Autowired
    private  EmployeeService employeeService;

    /**
     * 用户名查重
     * @param lastName 要检查的用户名
     * @return 结果状态
     */
    @RequestMapping("/checkuser")
    @ResponseBody
    public Msg checkUser(@RequestParam("lastName") String lastName){
        boolean status=employeeService.checkUser(lastName);
        if(status) {
            return Msg.success();
        }else {
            return Msg.fail();
        }
    }

    /**
     * 新增员工
     * 1.支持JSR303校验
     * 2.导入Hibernate-Validator
     *
     * @param employee 要保存的员工对象
     * @return 结果状态
     */
    //@Valid表示传入的参数的数据需要进行数据验证
    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            //校验失败，在模态框中显示错误信息
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors=result.getFieldErrors();
            for (FieldError error:errors){
                map.put(error.getField(),error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        }
        employeeService.saveEmp(employee);
        return Msg.success();
    }

    /**
     * 利用ajax，前端可以主动申请得到json数据，可以支持多种前端申请（web，安卓...）
     *
     * @param pn 当前的页数
     * @return json数据
     */
    @RequestMapping("/all")
    @ResponseBody
    public Msg getEmpWithJson(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //引入分页插件并调用,传入页码和每页的数据个数
        PageHelper.startPage(pn,5);

        //startPage后面紧跟的一个查询就是分页查询
        List<Employee> emps=employeeService.getAll();

        //用PageInfo来包装得到的数据，里面封装了详细的分页信息和我们查询的数据
        //传入参数除了list，还可以带一个整数，表示连续显示的页数
        PageInfo<Employee> page=new PageInfo<>(emps,5);

        return Msg.success().add("pageInfo",page);
    }

    /**
     * 通过id获取员工信息
     * @param id 要得到的员工的id
     * @return 查询得到的员工
     */
    @RequestMapping(value = "emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){

        Employee employee=employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    /**
     * 过来的参数id应该与pojo对象的属性名一致，这样才是将id封装进去
     *
     * 请求体中有数据，但Employee对象封装不上
     * 原因：
     * Tomcat：
     *      1.将请求题中的数据，封装进一个map
     *      2.request.getParameter("lastName")就会从map中取值
     *      3.SpringMVC封装POJO对象的时候会把POJO中每个属性的值通过request.getParameter()得到
     *
     * AJAX发送put请求的问题：
     *      put请求，请求体中的数据，request.getParameter("lastName")拿不到
     *      因为Tomcat不会封装put请求中请求体的内容到map，只有POST形式才会封装过去
     *
     * 所以我们要能支持发送PUT请求还要封装请求体中的数据
     * 配置httpPutFormContentFilter
     * 作用：将请求体中的数据解析包装成一个map，
     * 员工的更新方法
     * @param employee 要更新的员工对象
     * @return 更新状态
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.update(employee);
        return Msg.success();
    }

    /**
     * 单个删除，批量删除二合一
     * 单个删除：带一个单独的id。批量删除：多个id之间用-隔开
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids){
        if (ids.contains("-")){
            //批量删除
            List<Integer> intIds=new ArrayList<>();
            String[] strIds=ids.split("-");
            for (String strid:strIds){
                intIds.add(Integer.parseInt(strid));
            }
            employeeService.deleteBatch(intIds);
        }else {
            //单个删除
            employeeService.deleteEmpById(Integer.parseInt(ids));
        }
        return Msg.success();
    }

    /*单个删除
      @ResponseBody
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
    public Msg deleteEmpById(@PathVariable("id") Integer id){
        employeeService.deleteEmpById(id);
        return Msg.success();
    }*/
    /**
     * 使用jsp的后端，返回的是一个页面，使用不够多面
     * @param pn 查询的页数，默认为1
     * @param model 容器，供前端调用的数据
     * @return list页面显示信息
     */
   /* @RequestMapping("/all")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1")Integer pn,
                          Model model){
        //引入分页插件并调用,传入页码和每页的数据个数
        PageHelper.startPage(pn,5);

       //startPage后面紧跟的一个查询就是分页查询
        List<Employee> emps=employeeService.getAll();

        //用PageInfo来包装得到的数据，里面封装了详细的分页信息和我们查询的数据
        //传入参数除了list，还可以带一个整数，表示连续显示的页数
        PageInfo<Employee> page=new PageInfo<>(emps,5);

        //添加到model中形成键值对，方便前端取值
        model.addAttribute("pageInfo",page);

        return "list";
    }*/
}

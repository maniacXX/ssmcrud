

import com.itlc.crud.bean.Dept;
import com.itlc.crud.bean.Employee;
import com.itlc.crud.dao.DeptMapper;

import com.itlc.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 *
 * @author Administrator
 * @date 2018/6/4 16:54
 *
 * 推荐Spring的项目就使用Spring的单元测试，可以自动注入我们需要的组件
 *  1.导入SpringTest模块
 *  2.@ContextConfiguration指定Spring配置文件的位置
 *  3.直接@Autowired要使用的组件
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class MapperTest {

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    //批量的sqlSession
    @Autowired
    SqlSession sqlSession;

    /**
    * 测试DepartmentMapper
    * */
    @Test
    public void testCRUD(){
        /*//原生方法
        //1.创建springIOC容器
        ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-config.xml");
        //2.从容器中获取mapper
        DeptMapper mapper=ioc.getBean(DeptMapper.class);

        System.out.println(mapper);*/

        System.out.println(deptMapper);


        System.out.println(employeeMapper.selectByExample(null));
        //1.插入几个部门
       /* deptMapper.insertSelective(new Dept(null,"资源部"));
        deptMapper.insertSelective(new Dept(null,"科技部"));
*/
        //2.生成员工数据
        //employeeMapper.insertSelective(new Employee(null,"jj","M","jj@itlc.com",3));


        //3.批量插入多个员工；使用可以执行批量操作的sqlSession
        //非批量操作，多次编译sql，操作时间较长
        /*for (){
            employeeMapper.insertSelective(new Employee(null,"jj","M","jj@itlc.com",3));
        }*/

        //批量操作
        EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
        for (int i=0;i<1000;i++){
            String s=UUID.randomUUID().toString().substring(0,5)+i;
            employeeMapper.insertSelective(new Employee(null,s,"M",s+"@itlc.com",3));
        }
    }
}

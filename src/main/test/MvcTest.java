/**
 * @author Administrator
 * @date 2018/6/7 13:11
 */

import com.github.pagehelper.PageInfo;
import com.itlc.crud.bean.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用Spring测试模块提供的测试请求功能，测试crud请求的正确性
 * @WebAppConfiguration使得WebApplicationContext context可以使用@Autowired
 *  测试需要sevlet3.0以上的支持
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-config.xml",
        "file:src/main/webapp/WEB-INF/springmvc-config.xml" })
public class MvcTest {
    //传入SpringMVC的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取到处理结果
    private MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public  void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/employee/all").param("pn", "1"))
                .andReturn();
        //请求成功以后，请求域中会有pageInfo，可以取出验证
        MockHttpServletRequest request=result.getRequest();

        PageInfo attr= (PageInfo) request.getAttribute("pageInfo");

        System.out.println(attr);
        System.out.println("当前页码:"+attr.getPageNum());
        System.out.println("总页码:"+attr.getPages());
        System.out.println("总记录数:"+attr.getTotal());
        System.out.println("连续显示的页码：");
        int[] nums=attr.getNavigatepageNums();
        for (int i:nums){
            System.out.println(" "+i);
        }
        //获取员工数据
        List<Employee> list=attr.getList();
        for (Employee employee:list){
            System.out.println(employee);
        }
    }
}

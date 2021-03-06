package com.itlc.crud.dao;

import com.itlc.crud.bean.Dept;
import com.itlc.crud.bean.DeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    long countByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int deleteByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int insert(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int insertSelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    List<Dept> selectByExample(DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    Dept selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int updateByExampleSelective(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int updateByExample(@Param("record") Dept record, @Param("example") DeptExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int updateByPrimaryKeySelective(Dept record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dept
     *
     * @mbg.generated Mon Jun 04 15:11:51 CST 2018
     */
    int updateByPrimaryKey(Dept record);
}
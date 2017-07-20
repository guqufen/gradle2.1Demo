package net.fnsco.withhold.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import net.fnsco.withhold.service.dao.UserDAO;
import net.fnsco.withhold.service.dao.UserPO;
import tk.mybatis.mapper.entity.Example;

public class WithholdService {
    @Autowired
    private UserDAO mapper;

    public void getList() {

        //查询全部
        List<UserPO> countryList = mapper.select(new UserPO());
        //总数
        Assert.assertEquals(183, countryList.size());
        //通用Example查询
        Example example = new Example(UserPO.class);
        example.createCriteria().andGreaterThan("id", 100);
        countryList = mapper.selectByExample(example);
        Assert.assertEquals(83, countryList.size());
        //MyBatis-Generator生成的Example查询
        //CountryExample example2 = new CountryExample();
        //example2.createCriteria().andIdGreaterThan(100);
        //countryList = mapper.selectByExample(example2);
        //Assert.assertEquals(83, countryList.size());
    }
}

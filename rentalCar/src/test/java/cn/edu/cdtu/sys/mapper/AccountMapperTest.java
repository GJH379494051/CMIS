package cn.edu.cdtu.sys.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AccountMapperTest {

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void transfer() {
        accountMapper.transferIn("jerry", 1000.0);
        accountMapper.transferOut("tom", 1000.0);
    }
}
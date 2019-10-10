package day04.practice;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext_anno.xml")
public class Test {
    @Autowired
    private Transfer transfer;

    @org.junit.Test
    public void Test(){
        transfer.transfer("吃饭支出","工资收入",345);
    }
}

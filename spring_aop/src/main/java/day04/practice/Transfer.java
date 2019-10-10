package day04.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Transfer {
    @Autowired
    private InMoney inMoney;
    @Autowired
    private OutMoney outMoney;
    @Transactional
    public void transfer(String inname,String outname,double money){
        outMoney.out(outname,money);
       // int i =1/0;
        inMoney.In(inname,money);
    }
}

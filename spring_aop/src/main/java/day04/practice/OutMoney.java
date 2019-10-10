package day04.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OutMoney {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void out(String name,double money){
        jdbcTemplate.update("UPDATE bill set zmoney=zmoney-? where zname=?",money,name);
    }
}

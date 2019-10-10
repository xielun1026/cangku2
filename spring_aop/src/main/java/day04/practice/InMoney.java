package day04.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class InMoney {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void In(String name,double money){
        jdbcTemplate.update("UPDATE bill set zmoney=zmoney+? WHERE zname=?",money,name);
    }
}

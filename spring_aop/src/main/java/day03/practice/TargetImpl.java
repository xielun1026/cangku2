package day03.practice;

import org.springframework.stereotype.Component;

@Component
public class TargetImpl implements Target {
    public void save() {
        System.out.println("save running");
    }
}

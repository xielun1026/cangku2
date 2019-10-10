package practice.domain;

import java.util.List;

public class VO {
    private List<User> list;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "VO{" +
                "list=" + list +
                '}';
    }
}

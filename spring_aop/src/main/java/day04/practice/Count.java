package day04.practice;

public class Count {
    private String name;
    private double money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Count{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}

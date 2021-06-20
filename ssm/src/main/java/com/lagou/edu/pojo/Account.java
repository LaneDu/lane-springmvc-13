package com.lagou.edu.pojo;

/**
 * @author lane
 * @date 2021年04月07日 下午3:09
 */
public class Account {
    private String name;
    private String cardNo;
    private Integer money;

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", money=" + money +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.sql.Time;
import company.Util.Sex;

/**
 *
 * @author Bence
 */
public abstract class Person {

    private String name;
    private Time born;
    private Sex sex;
    private int payment;
    private int money;
    private int value; //a személy munkaértéke, ennyi pénzt kap a cég, ha dolgozik nála

    public Person(String name, Time born, Sex sex, int payment, int value, int money) {
        this.name = name;
        this.born = born;
        this.sex = sex;
        this.payment = payment;
        this.value = value;
        this.money = money;
    }

    public Person(String name, Time born, Sex sex, int payment, int value) {
        this.name = name;
        this.born = born;
        this.sex = sex;
        this.payment = payment;
        this.value = value;
        this.money = 0;
    }

    public int getMoney() {
        return money;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setBorn(Time born) {
        this.born = born;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public Time getBorn() {
        return born;
    }

    public Sex getSex() {
        return sex;
    }

    public int getPayment() {
        return payment;
    }

    public void quitCompany(Company company) {
        if (company.getBosses().contains(this)) {
            company.removeBoss((Boss) this);
        } else if (company.getWorkers().contains(this)) {
            company.removeWorker((Worker) this);
        } else {
            System.out.println(getName() + " nem tagja a(z) " + company.getName() + " cégnek, így nem is mondhat ott fel!");
        }
    }

    public abstract void work(Company company);
}

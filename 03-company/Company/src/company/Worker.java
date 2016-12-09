/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Bence
 */
public class Worker extends Person {

    public Worker(String name, Time born, Sex sex, int money, int value) {
        super(name, born, sex, money, value);
    }

    public void work(Company company) {
        if (company.getWorkers().contains(this)) {
            System.out.println("Dolgozó  " + this.getName() + " dolgozik a(z) " + company.getName() + " cégnél.");
            company.setBudget(company.getBudget() + this.getValue());
        } else {
            System.out.println("Dolgozó  " + this.getName() + " nem tagja a(z) " + company.getName() + " cégnek!");
        }
    }
}

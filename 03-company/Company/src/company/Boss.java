/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.util.Date;

/**
 *
 * @author Bence
 */
public class Boss extends Person {

    public Boss(String name, Date born, Util.Sex sex, int money, int value) {
        super(name, born, sex, money, value);
    }

    public void work(Company company) {
        if (company.getBosses().contains(this)) {
            System.out.println("Főnök  " + this.getName() + " dolgozik.");
            company.setBudget(company.getBudget() + this.getValue());
        } else {
            System.out.println("Főnök  " + this.getName() + " nem tagja a(z)" + company.getName() + "+ cégnek!");
        }
    }

    public void fireWorker(Worker worker, Company company) {
        if (company.getBosses().contains(this)) {
            if((company.getWorkers().contains(worker))){
            System.out.println("Főnök  " + this.getName() + " kirúgta " + worker.getName() + " dolgozót.");}
            else {System.out.println(this.getName() + " nem tagja a(z)" + company.getName() + "+ cégnek, így nem is rúgható ki onnan!");}
            company.removeWorker(worker);
        } else {
            System.out.println("Főnök  " + this.getName() + " nem tagja a(z)" + company.getName() + " cégnek, így nem is rúghat ki onnan senkit!");
        }

    }

}

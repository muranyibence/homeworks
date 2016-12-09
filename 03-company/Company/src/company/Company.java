/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Bence
 */
public class Company {

    private List<Worker> workers;
    private List<Boss> bosses;
    private List<String> activities;
    private String location;
    private int budget;
    private String name;

    public Company(String location, int budget, String name) {
        this.activities = new ArrayList<String>();
        this.workers = new ArrayList<Worker>();
        this.bosses = new ArrayList<Boss>();

        this.location = location;
        this.budget = budget;
        this.name = name;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Boss> getBosses() {
        return bosses;
    }

    public List<String> getActivities() {
        return activities;
    }

    public String getLocation() {
        return location;
    }

    public int getBudget() {
        return budget;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWorker(Worker worker) {
        if (workers.contains(worker)) {
            System.out.println(worker.getName() + " már tagja a(z)" + this.getName() + "cégnek!");
        } else {
            workers.add(worker);
        }
    }

    public void addBoss(Boss boss) {
        if (workers.contains(boss)) {
            System.out.println(boss.getName() + " már tagja a(z)" + this.getName() + "cég főnökeinek!");
        } else {
            bosses.add(boss);
        }
    }

    public void addActicity(String activity) {
        if (workers.contains(activity)) {
            System.out.println(activity + " már része a(z)" + this.getName() + "cég tevékenyésgeinek!");
        } else {
            activities.add(activity);
        }
    }

    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }
    
       public void removeBoss(Boss boss) {
        bosses.remove(boss);
    }


    public void giveSalary(Person person) {
        if (workers.contains(person) || bosses.contains(person)) {
            if (getBudget() < person.getValue()) {
                System.out.println(this.getName() + " cégnek " + (person.getValue() - getBudget()) + " hiánya van, hogy kifizesse " + person.getName() + "-t.");
            } else {
                System.out.println(person.getName() + " kifizetve.");
                setBudget(getBudget() - person.getValue());
                person.setMoney(person.getMoney() + person.getValue());
            }

        }

    }

    public void giveSalaryAll() {

        for (Boss boss: bosses) {
           
            giveSalary(boss);
        }

        for (Worker worker: workers) {
            giveSalary(worker);
        }
    }
}

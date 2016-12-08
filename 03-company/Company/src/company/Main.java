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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // objektumok létrehozása

        Company c1 = new Company("Budapest Ferenc Körút 40.", 1000, "Cég1");
        Company c2 = new Company("Budapest Ferenc Körút 41.", 50, "Cég2");

        Worker w1 = new Worker("Dolgozó1", new Date(1987, 11, 3), Util.Sex.MALE, 0, 100);
        Worker w2 = new Worker("Dolgozó2", new Date(1986, 8, 11), Util.Sex.FEMALE, 50, 80);
        Worker w3 = new Worker("Dolgozó1", new Date(1982, 1, 5), Util.Sex.MALE, 0, 110);

        Boss b1 = new Boss("Boss1", new Date(1966, 2, 5), Util.Sex.MALE, 400, 200);
        Boss b2 = new Boss("Boss2", new Date(1982, 6, 11), Util.Sex.FEMALE, 300, 220);
        //munkások hozzáadása
        c1.addWorker(w1);
        c1.addWorker(w2);
        c2.addWorker(w3);

        //főnökök hozzáadása
        c1.addBoss(b1);
        c2.addBoss(b2);
        //dolgozó dolgozik cégénél
        w1.work(c1);
        //főnök dolgozik cégénél
        b2.work(c2);
        //dolgozó dolgozik olyan cégnél, aminek nem tagja
        w2.work(c2);

        //fizetést kap mindenki
        c1.giveSalaryAll();
        //fizetés második cégnél,ahol nincs elég pénz
        c2.giveSalaryAll();
        
        //b1 kirúgatja w1-et, aki így már nem dolgozhat c1-nél
        b1.fireWorker(w1, c1);
        w1.work(c1);
        
        //b2 kirúgatná w2-t c1-től, de mivel b2 nem főnöke c1-nek, ez nem sikerül
        b2.fireWorker(w2, c1);
        
        //w2 felmond c1-nél, így már ő sem dolgozhat ott
        w2.quitCompany(c1);
        w2.work(c1);
        
        //w3 felmondana c1-nél, de ott se volt, így nem sikerül
        w3.quitCompany(c1);
    }

}

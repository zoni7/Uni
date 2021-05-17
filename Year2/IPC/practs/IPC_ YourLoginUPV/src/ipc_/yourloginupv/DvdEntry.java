/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc_.yourloginupv;

/**
 *
 * @author USUARIO
 */
public class DvdEntry {
    private String title;
    private int year;
    public DvdEntry(String title, int year) {
        this.title = title;
        this.year = year;
    }
    public String toString(){
        return title+", " +year;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
}

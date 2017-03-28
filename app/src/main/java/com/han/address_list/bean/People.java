package com.han.address_list.bean;

import com.han.address_list.utils.Contact;
import com.han.address_list.utils.SortToken;

/**
 * Created by aaa on 2017/3/22.
 */

public class People extends Contact {

    String name;
    String number;
    String room;
    int typeID;
    public SortToken sortToken = new SortToken();
    public People(String name, String number, String room, int typeID) {
        this.name = name;
        this.number = number;
        this.room = room;
        this.typeID = typeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getRoom() {
        return room;
    }

    public int getTypeID() {
        return typeID;
    }
}

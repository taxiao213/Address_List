package com.han.address_list.bean;

import java.util.ArrayList;

/**
 * Created by aaa on 2017/3/24.
 */

public class Content {


    public Content() {

    }

    public ArrayList<Room> getRoom() {
        ArrayList<Room> mRooms = new ArrayList<>();
        Room room;
        for (int i = 1; i < 10; i++) {
            room = new Room("科室" + i, i);
            mRooms.add(room);
        }
        return mRooms;
    }

    public ArrayList<People> getPeople() {

        ArrayList<People> mPeople = new ArrayList<>();

        People people;
        for (int i = 0; i < 10; i++) {
            people = new People("小明" + i, "1823407822" + i, "科室1", 1);
            mPeople.add(people);

        }


        for (int i = 0; i < 9; i++) {
            people = new People("小话" + i, "1822512822" + i, "科室2", 2);
            mPeople.add(people);

        }


        for (int i = 0; i < 8; i++) {
            people = new People("小石" + i, "1822512700" + i, "科室3", 3);
            mPeople.add(people);

        }


        for (int i = 0; i < 7; i++) {
            people = new People("小米" + i, "1822518880" + i, "科室4", 4);
            mPeople.add(people);

        }


        for (int i = 0; i < 6; i++) {
            people = new People("小户" + i, "1822512777" + i, "科室5", 5);
            mPeople.add(people);

        }


        for (int i = 0; i < 7; i++) {
            people = new People("小雨" + i, "1822512780" + i, "科室6", 6);
            mPeople.add(people);

        }


        for (int i = 0; i < 8; i++) {
            people = new People("小宋" + i, "1822518790" + i, "科室7", 7);
            mPeople.add(people);

        }

        return mPeople;

    }
}

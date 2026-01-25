package com.soumya.sec12;

import com.soumya.common.Util;
import com.soumya.sec12.assesment.SlackMember;
import com.soumya.sec12.assesment.SlackRoom;

public class Lec08SlackAssignment {
    public static void main(String[] args) {
        var room = new SlackRoom("reactor");
        var sam = new SlackMember("sam");
        var ram = new SlackMember("ram");
        var madhu = new SlackMember("madhu");

        //add 2 member
        room.addMember(sam);
        room.addMember(ram);

        ram.says("Hi.. All");
        Util.sleepSecond(4);

        sam.says("Hi.. Good Morning");
        ram.says("Quite");
        Util.sleepSecond(4);

        room.addMember(madhu);
        madhu.says("Hi guys... good afternoon");



    }
}

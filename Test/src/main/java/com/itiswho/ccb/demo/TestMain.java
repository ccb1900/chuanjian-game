package com.itiswho.ccb.demo;
import com.itiswho.ccb.proto.AddressBookProtos;
import com.itiswho.ccb.server.NettyServer;

public class TestMain {
    public static void main(String[] args) throws Exception {
        System.out.println("sadsad");
        NettyServer.start(7002);
//        AddressBookProtos.Person.Builder person = new AddressBookProtos.Person.newBuilder();
    }
}

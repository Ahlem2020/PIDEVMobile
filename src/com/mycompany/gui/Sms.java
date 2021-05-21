///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.gui;
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//
///**
// *
// * @author essia
// */
//public class Sms {
//    // Find your Account Sid and Auth Token at twilio.com/console
//    public static final String ACCOUNT_SID = System.getenv("AC5316f05deca17f0e33e30ff9bcdb97fd");
//    public static final String AUTH_TOKEN = System.getenv("1a7a9d13fe63f9950060ce7a3f94594d");
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        Message message = Message
//                .creator(new PhoneNumber("+14159352345"), // to
//                        new PhoneNumber("+14158141829"), // from
//                        "Where's Wallace?")
//                .create();
//
//        System.out.println(message.getSid());
//    }
//}
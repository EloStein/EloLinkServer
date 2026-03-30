//package de.spring.elolink_spring.controller;
//
//import de.spring.elolink_spring.dtos.UserDto;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.time.LocalTime;
//
//@Controller
//public class MiscController {
//
//    private static final String MA = "Mathe";
//    private static final String DE = "Deutsch";
//    private static final String EN = "Englisch";
//    private static final String FR = "Französisch";
//    private static final String BI = "Biologie";
//    private static final String PY = "Physik";
//    private static final String ER = "Erdkunde";
//    private static final String RE = "Religion";
//    private static final String SK = "Sozialkunde";
//    private static final String CH = "Chemie";
//    private static final String GE = "Geschichte";
//    private static final String MU = "Geschichte";
//    private static final String BK = "Bildende Kunst";
//
//
//
//
//
//    @RequestMapping(value = "/elolink/misc/{user}", method = RequestMethod.POST)
//    public String getAppleApp(@RequestBody String user){
//        switch (user){
//            case "Ole" : Ole();
//            case "Felix" : Ole();
//            case "Mat" : Ole();
//
//        }
//
//
//        return "AB";
//    }
//
//
//
//    public String Ole(){
//        LocalTime time = LocalTime.now();
//
//
//    }
//
//    public String Felix(){
//    }
//
//    public String Mat(){
//    }
//
//
//}

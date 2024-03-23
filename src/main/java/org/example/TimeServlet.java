package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.time.zone.ZoneRulesException;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(value = "/time")
//public class TimeServlet extends HttpServlet {
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String timezone = req.getParameter("timezone");
//
//
////        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
////        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss O"));
//
//
//        resp.setContentType("text/html; charset=utf-8");
//        resp.getWriter().write(parseTimezone(timezone));
//        resp.getWriter().close();
//    }
//
//    private String parseTimezone(String zone) {
//
////        if (zone == null) return currentTime;
//
//        Matcher matcher = Pattern.compile("UTC([+-]\\d+)").matcher(zone);
//        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC"));
//        if (matcher.find()) {
//            int hours = Integer.parseInt(matcher.group(1));
//            if (hours>= -12 && hours <= 14) {
//                if (hours > 0) {
//                    return zonedDateTime.plusHours(hours).format(FORMATTER) + " UTC+" + hours;
//                } else if (hours < 0) {
//                    hours = hours * -1;
//                    return zonedDateTime.minusHours(hours).format(FORMATTER) + " UTC-" + hours;
//                }
//            }
//            return zonedDateTime.format(FORMATTER) + " UTC";
//        }
//        return null;
//    }
//
//}

//@WebServlet(value = "/time")
//public class TimeServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req,
//                         HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html; charset=utf-8");
//        String timezoneParam = req.getParameter("timezone");
//        System.out.println("Received timezone parameter: " + timezoneParam);
//
//        ZoneId zoneId = (timezoneParam != null && !timezoneParam.isEmpty()) ? ZoneId.of(timezoneParam) : ZoneId.of("UTC");
//        System.out.println("Received timezone parameter: " + zoneId);
//
//        String currentTime = ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
//        resp.getWriter().write(currentTime);
//        resp.getWriter().close();
//    }
//
//}

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    private String currentTime;
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String timezoneParam = req.getParameter("timezone");
        System.out.println("Received timezone parameter: " + timezoneParam);

        // Відновлюємо символ "+" перед передачею до методу ZoneId.of()
        if (timezoneParam != null) {
            timezoneParam = timezoneParam.replace(" ", "+");
        }

        ZoneId zoneId = (timezoneParam != null && !timezoneParam.isEmpty()) ? ZoneId.of(timezoneParam) : ZoneId.of("UTC");
        System.out.println("ZoneId: " + zoneId);

        currentTime = ZonedDateTime.now(zoneId).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));
        resp.getWriter().write(currentTime);
        resp.getWriter().close();
    }
    @Override
    public void destroy() {
        currentTime = null;
    }
}







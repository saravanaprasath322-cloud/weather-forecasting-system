package com.project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.net.*;
import java.util.Scanner;

public class WeatherServlet extends HttpServlet {
    private static final String API_KEY = "your_openweathermap_api_key";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String city = request.getParameter("city");
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" 
                        + city + "&appid=" + API_KEY + "&units=metric";

        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder jsonResponse = new StringBuilder();
        while (sc.hasNext()) {
            jsonResponse.append(sc.nextLine());
        }
        sc.close();

        request.setAttribute("weatherData", jsonResponse.toString());
        RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
        rd.forward(request, response);
    }
}

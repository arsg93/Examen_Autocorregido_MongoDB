/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class realizarExamenServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String DNI = request.getParameter("DNI");
        String tipoExamen = request.getParameter("examen");
//        request.setAttribute("DNI", DNI);
//        request.setAttribute("examen", tipoExamen);
//        RequestDispatcher rd = request.getRequestDispatcher("examen.jsp");
//        rd.forward(request, response);

 

    }

}

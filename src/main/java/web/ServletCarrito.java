/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author david
 */
@WebServlet("/ServletCarrito")
public class ServletCarrito extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        //creamos o recuperamos el objeto httpsession
        HttpSession sesion = request.getSession();
        //recuperamos la lista de los articulos si ya esta lista
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        //verficamos si la lista de articulos existe
        if (articulos == null) {
            //iniciamos la lista de articulos
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);

        }
        //procesamos el nuevo articulo
        String articuloNuevo = request.getParameter("articulo");
        //revisamos y agregamos el articulo nuevo
        if (articuloNuevo != null && !articuloNuevo.trim().equals("")) {
            articulos.add(articuloNuevo);
        }
        try ( //mandar a imprimir la lista de articulos
                PrintWriter out = response.getWriter()) {
            out.println("<h1>Lista de Articulos</h1>");
            out.println("<br>");
            //interamos todos los articulos
            for(String articulo: articulos){
                out.println("<li>"+articulo+"</li>");
                
            }
            //agregamos un link para regresar al inicio
            out.println("<br>");
            out.println("<a href='/CarritoCompras'>regresar al inicio </a>");
        }
                
    }

}
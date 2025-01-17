package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        try {
            //Recoger el id del socio
            int socioID = Integer.parseInt(request.getParameter("codigo"));

            //Borra el socio que tiene ese ID
            this.socioDAO.delete(socioID);

            //Listado actualizado con el socio borrado
            List<Socio> listado = this.socioDAO.getAll();
            request.setAttribute("listado", listado);

            //Redirección interna del servidor
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error de borrar socio!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
        }

        //Hacer efectiva la redirección
        dispatcher.forward(request, response);
    }
}

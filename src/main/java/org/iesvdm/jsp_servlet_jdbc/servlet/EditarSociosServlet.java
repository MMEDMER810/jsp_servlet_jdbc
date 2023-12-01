package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jdk.jshell.execution.Util;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EditarSociosServlet", value = "/EditarSociosServlet")
public class EditarSociosServlet extends HttpServlet {

    //EL SERVLET TIENE INSTANCIADO EL DAO PARA ACCESO A BBDD A LA TABLA SOCIO
    private SocioDAO socioDAO = new SocioDAOImpl();

    //MÉTODO PARA RUTAS GET /EditarSociosServlet
    //PARA LA RUTA /EditarSociosServlet VA A MOSTRAR LA JSP DE formularioEditarSocioB.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean valida = true;
        int socioID = -1;

        try {
            socioID = Integer.parseInt((request.getParameter("codigo")));

        } catch (Exception ex) {
            ex.printStackTrace();
            valida = false;
        }

        if (valida) {
            Optional<Socio> optionalSocio = this.socioDAO.find(socioID);

            if (optionalSocio.isPresent()) {
                Socio socioAEditar = optionalSocio.get();
                request.setAttribute("socioAEditar", socioAEditar);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocioB.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Socio> optionalSocio = UtilServlet.validaEditar(request);

        if (optionalSocio.isPresent()) {
            Socio socio = optionalSocio.get();
            socio.setSocioId(Integer.parseInt(request.getParameter("socioID")));

            this.socioDAO.update(socio);

            List<Socio> listado = this.socioDAO.getAll();

            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");

        } else {
            request.setAttribute("error", "Error de editar socio!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarSocioB.jsp");
        }

        dispatcher.forward(request, response);

    }

}
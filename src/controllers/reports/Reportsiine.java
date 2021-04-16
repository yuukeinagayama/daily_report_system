package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.Reportgood;
import utils.DBUtil;

@WebServlet("/reports/iine")
public class Reportsiine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Reportsiine() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Reportgood rg = new Reportgood();

        rg.setEmployee((Employee) request.getSession().getAttribute("login_employee"));

        EntityManager em = DBUtil.createEntityManager();

        Report rp = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        rg.setReport(rp);

        em.getTransaction().begin();
        em.persist(rg);

        em.getTransaction().commit();

        em.close();

        response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("id"));
    }

}

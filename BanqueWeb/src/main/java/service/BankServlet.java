package service;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import metier.BanqueLocale;
import metier.entities.Compte;


//in servlet http client chrome , http://localhost:8080/BanqueWeb/bank
//we dont need jndi.properties et jboss-ejb-client.properties

//*********************Rest web service************************ client web service will be postman or advanced rest client google chrome

@WebServlet("/BankServlet")
public class BankServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtain a reference to the EJB using JNDI
            InitialContext ctx = new InitialContext();
            BanqueLocale banque = (BanqueLocale) ctx.lookup("java:global/BanqueEAR/BanqueEJB/BK!metier.BanqueLocale");

            // Call EJB methods
            List<Compte> cptes = banque.listComptes();

			for (Compte compte : cptes) {

				System.out.println(compte.getCode() + ":" + compte.getSolde());
				 // You can use the retrieved data in your servlet response
	            response.getWriter().write("Account Details: " + compte.getCode() +" "+compte.getSolde());

			} // Replace 1L with the account code you want to retrieve
            // Perform other EJB operations as needed

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

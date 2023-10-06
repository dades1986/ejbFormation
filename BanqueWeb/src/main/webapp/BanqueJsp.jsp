<%@page import="metier.entities.Compte"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bank Accounts</title>
</head>
<body>
    <h1>Bank Accounts</h1>
    <%
        try {
            // Perform JNDI lookup to access the EJB
            javax.naming.InitialContext ctx = new javax.naming.InitialContext();
            metier.BanqueLocale bank = (metier.BanqueLocale) ctx.lookup("java:global/BanqueEAR/BanqueEJB/BK!metier.BanqueLocale");
            
            // Call methods on the EJB
            List<Compte> cptes = bank.listComptes();
    %>
    <table>
        <tr>
            <th>Account Code</th>
            <th>Account Balance</th>
        </tr>
        <% for (Compte compte : cptes) { %>
            <tr>
                <td><%= compte.getCode() %></td>
                <td><%= compte.getSolde() %></td>
            </tr>
        <% } %>
    </table>
    <%
        } catch (Exception e) {
            out.println("An error occurred: " + e.getMessage());
        }
    %>
</body>
</html>

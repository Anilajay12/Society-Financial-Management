<%@page import="com.virtusa.society.dao.DBConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <select name="rent" id="rent">
            <option disabled="" selected="Rent">Rent</option>
            <%

                int cid = Integer.parseInt(request.getParameter("pid"));
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select rent from plot where pid='" + cid + "'");
                while (rs.next()) {
            %>
            <option><%=rs.getDouble("rent")%></option>
            <%
                }
            %>
        </select>    </body>
</html>

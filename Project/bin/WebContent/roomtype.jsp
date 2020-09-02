<%@page import="java.sql.ResultSet"%>
<%@page import="com.virtusa.society.dao.DBConnection"%>
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
        <select name="plotno" id="plotid">
            <option disabled="" selected="Room Type"></option>
            <%

                int id = Integer.parseInt(request.getParameter("id"));
                Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select pid, plotnumber from plot where status=false and id='" + id + "'");
                while (rs.next()) {
            %>
            <option value="<%=rs.getInt("pid")%>"><%=rs.getString("plotnumber")%></option>
            <%
                }
            %>
            
        </select>
   </body>
</html>
<script>
	$(document).ready(function() {
		$("#plotid").on("change", function() {
			var pid = $("#plotid").val();
			var id = $("#id").val();
			if (pid === "") {
				$("#error").html("required");
				return false;
			} else {
				$("#error").html("");
				$.ajax({
					url : "rent.jsp",
					data : {
						pid : pid,
						id : id
					},
					method : "POST",
					success : function(data) {
						$("#suc_msg").html(data);
						//$("#q").css("color", "red") ;
						//$("#y").css("color", "blue") ;
						//$("#c").css("color", "green") ;
					}

				});
			}
		});
	});
</script>

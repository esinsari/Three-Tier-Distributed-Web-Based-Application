<%--   
 /*
 * Name: Esin Sari
 * Course: CNT 4714 – Fall 2021 – Project Three
 * Assignment title: A Three-Tier Distributed Web-Based Application
 * Date: August 1, 2021 
*/
--%>

<!doctype html>

<%
   String result = (String) session.getAttribute("result");
   
    if(result == null) {
        result = " ";
   }

   String sqlCommands = (String) session.getAttribute("sqlCommands");
	
   if(sqlCommands == null) {
       sqlCommands = " ";
   }
%>      

<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
        crossorigin="anonymous">

    <title>Project 3</title>

    <script src="reset.js"></script>  
    
</head>

<body>


    <div class="container-fluid " style="background-color: navy; color: white;">
        <row class="row justify-content-center">

            <h1 class="text-center col-sm-12 col-md-12 col-lg-12">
                Welcome to the Summer 2021 Project 3 Enterprise Database System 
            </h1>

            <h2 class="text-center col-sm-12 col-md-12 col-lg-12">
                A Servlet/JSP-based Multi-tiered Enterprise Application Using A Tomcat Container
            </h2>

            <div class="text-center col-sm-12 col-md-12 col-lg-12">
                You are connected to the Project 3 Enterprise System database as a root user.
            </div>

            <div class="text-center col-sm-12 col-md-12 col-lg-12">
                Please enter any valid SQL query or update command.
            </div>

	    <hr size="5" width="100%" color="white">

            <form method = "POST" action = "webserver" style="margin-top: 15px;" class="text-center">
                <div class="form-group row">
                    <div class=" col-sm-12 col-md-12 col-lg-12">
                        <textarea id="sqlCommands" name="sqlCommands" class="form-control" rows="8" cols="50"><%= sqlCommands %></textarea>
                    </div>
                </div>

                <input type="submit" value="Execute" name="execute" style="width: 100px;">
		<input type="reset" value="Clear" name="clear" style="width: 100px;">
            </form>

	    <div class="text-center col-sm-12 col-md-12 col-lg-12">
                All execution results will appear below this line.
            </div>

  	    <hr size="5" width="100%" color="white">

        </row>
    </div>


    <div class="text-center" style="background-color: blue; color: white;">
	<h2>Database Results</h2>
	<table>
        	<%= result %>
	</table>
    </div>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
        crossorigin="anonymous"></script>
</body>

</html>
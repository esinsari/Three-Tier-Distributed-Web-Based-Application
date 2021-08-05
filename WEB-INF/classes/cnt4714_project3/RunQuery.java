package cnt4714_project3;

import java.sql.*;
import java.util.Vector;

public class RunQuery {
	
	private Connection c;
	private Statement statement;
	private Vector<String> columns;
	private ResultSetMetaData metaData;
	
	public RunQuery(Connection c, Statement statement)
	{
		this.c = c;
		this.statement = statement;
	}
	
	public String runQuery(String query) throws SQLException {
		
		String opening = null;
		String column = null;
		String body = null;
		String closing = null;

		ResultSet resultSet = statement.executeQuery(query);

		metaData = resultSet.getMetaData();
		
		opening = "<div class='container-fluid'><div class='row justify-content-center'><div class='table-responsive-sm-10 table-responsive-md-10 table-responsive-lg-10'><table class='table'>";
		
		
		int numColumns = metaData.getColumnCount();
		column = setColumns(numColumns, metaData, column);
	
		body = "<tbody>";
				
		while (resultSet.next()) 
		{		
			body += "<tr>";
			
			for (int i = 1; i <= numColumns; i++) {
				if (i == 1)
					body += "<td scope'row'>" + resultSet.getString(i) + "</th>";
				else
					body += "<td>" + resultSet.getString(i) + "</th>";
			}
			
			body += "</tr>";
		}

		body += "</tbody>";

		closing = "</table></div></div></div>";
		
		return (opening + column + body + closing);
	}
	

	public String update(String query, Statement statement) throws SQLException
	{		
		String result = null;
		
		int numOfRowsUpdated = 0;
		int numOfShipmentsBefore;
		int numOfShipmentsAfter;
		int numOfRowsAffected;
		
		ResultSet quantityCheckBefore;
		ResultSet quantityCheckAfter;
		
		quantityCheckBefore = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		quantityCheckBefore.next();
		numOfShipmentsBefore = quantityCheckBefore.getInt(1);
		
		statement.executeUpdate("create table shipmentsBeforeUpdate like shipments");	
		statement.executeUpdate("insert into shipmentsBeforeUpdate select * from shipments");
		
		numOfRowsUpdated = statement.executeUpdate(query);

		result = "<div> The statement executed succesfully.</div><div>" + numOfRowsUpdated + " row(s) affected</div>";
		
		
		quantityCheckAfter = statement.executeQuery("select COUNT(*) from shipments where quantity >= 100");
		quantityCheckAfter.next();		
		numOfShipmentsAfter = quantityCheckAfter.getInt(1);
				
	
		if(numOfShipmentsBefore < numOfShipmentsAfter) 
		{
			result += "<div>" + numOfShipmentsBefore + " < " + numOfShipmentsAfter + "</div>";
			numOfRowsAffected = statement.executeUpdate("update suppliers set status = status + 5 where snum in ( select distinct snum from shipments left join shipmentsBeforeUpdate using (snum, pnum, jnum, quantity) where shipmentsBeforeUpdate.snum is null)");
			result += "<div>Business Logic Detected! - Updating Supplier Status</div>";
			result += "<div>Business Logic Updated " + numOfRowsAffected + " Supplier(s) status marks</div>";
		}
		else {
			result += "<div>" + numOfShipmentsBefore + " = " + numOfShipmentsAfter + "</div>";
			result += "<div>Business Logic has not triggered</div>";
		}

		statement.executeUpdate("drop table shipmentsBeforeUpdate");
		
		return result;	
	}
	
	/*GET METHODS*/
	public Vector<String> getColumns() throws SQLException
	{
		return this.columns;
	}
	
	/*SET METHODS*/
	public String setColumns(int numColumns, ResultSetMetaData metaData, String column) throws SQLException
	{
		column = "<thead class='thead-dark'><tr>";
	
		for (int i = 1; i <= numColumns; i++) {
			column += "<th scope='col'>" + metaData.getColumnName(i) + "</th>";
		}

		column += "</tr></thead>"; 
		
		return column;
	}
	

}

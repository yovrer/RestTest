package com.suhaib.dao;

import org.bson.Document;

public interface EmployeesDao {
	public String getAllEmployees();
	public String getEmployeeById (String id);
	public boolean updateEmployees (Document document);
	public boolean deleteEmployees (String id);
	public boolean insertEmployees(Document dcoument);
}

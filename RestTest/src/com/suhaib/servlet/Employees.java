package com.suhaib.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import com.suhaib.dao.EmployeesDaoImp;
import com.suhaib.util.Validate;

/**
 * Servlet implementation class Employees
 */
@WebServlet({ "/getAllEmployees", "/getEmployeeById", "/deleteEmployee", "/updateEmployee", "/addEmployee" })
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeesDaoImp employeesDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		employeesDao = new EmployeesDaoImp();
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Document jsonRespnse = new Document();
		response.setContentType("application/json");
		if (request.getRequestURI().contains("getAllEmployees")) {
			out.println(employeesDao.getAllEmployees());
		} else if (request.getRequestURI().contains("getEmployeeById")) {
			String[][] validate = { { "id", "\\w+" } };
			if (!Validate.validate(validate, request)) {
				jsonRespnse.append("state", false);
				jsonRespnse.append("masseage", "error with input parameter");
				out.println(jsonRespnse.toJson());
			} else {
				out.println(employeesDao.getEmployeeById(request.getParameter("id")));
			}
		} else if (request.getRequestURI().contains("deleteEmployee")) {
			String[][] validate = { { "id", "\\w+" } };
			if (!Validate.validate(validate, request)) {
				jsonRespnse.append("state", false);
				jsonRespnse.append("masseage", "error with input parameter");
				out.println(jsonRespnse.toJson());
			} else {
				if (employeesDao.deleteEmployees(request.getParameter("id"))) {
					jsonRespnse.append("state", true);
					out.println(jsonRespnse.toJson());
				} else {
					jsonRespnse.append("state", false);
					out.println(jsonRespnse.toJson());
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Document jsonRespnse = new Document();
		response.setContentType("application/json");
		if (request.getRequestURI().contains("addEmployee")) {
			String[][] validate = { { "firstName", "\\w+" }, { "lastName", "\\w+" }, { "age", "\\d+" },
					{ "gender", "\\w+" } };
			if (!Validate.validate(validate, request)) {
				jsonRespnse.append("state", false);
				jsonRespnse.append("masseage", "error with input parameter");
				out.println(jsonRespnse.toJson());
			} else {
				Document data = getDocumentFromRequest(request, validate);
				if (employeesDao.insertEmployees(data)) {
					jsonRespnse.append("state", true);
					out.println(jsonRespnse.toJson());
				} else {
					jsonRespnse.append("state", false);
					out.println(jsonRespnse.toJson());
				}
			}
		} else if (request.getRequestURI().contains("updateEmployee")) {
			String[][] validate = { { "id", "\\w+" }, { "firstName", "\\w+" }, { "lastName", "\\w+" },
					{ "age", "\\d+" }, { "gender", "\\w+" } };
			if (!Validate.validate(validate, request)) {
				jsonRespnse.append("state", false);
				jsonRespnse.append("masseage", "error with input parameter");
				out.println(jsonRespnse.toJson());
			} else {
				Document data = getDocumentFromRequest(request, validate);
				if (employeesDao.updateEmployees(data)) {
					jsonRespnse.append("state", true);
					out.println(jsonRespnse.toJson());
				} else {
					jsonRespnse.append("state", false);
					out.println(jsonRespnse.toJson());
				}
			}
		}
	}

	public Document getDocumentFromRequest(HttpServletRequest request, String[][] data) {
		Document document = new Document();
		for (int i = 0; i < data.length; i++) {
			document.append(data[i][0], request.getParameter(data[i][0]));
		}
		if (document.containsKey("age")) {
			document.put("age", Integer.parseInt((String) document.get("age")));
		}

		return document;
	}

}

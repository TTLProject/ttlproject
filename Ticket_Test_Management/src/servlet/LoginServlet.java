package servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeLoginDao;
import userbean.Userbean;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	
		
		/*String name = request.getParameter("name");
		String domain=request.getParameter("domain");
		String id =request.getParameter("id");*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		/*String confirmpassword=request.getParameter("confirmpassword");
		String email = request.getParameter("email");
		String webmail=request.getParameter("webmail");
		long mobile = Long.parseLong(request.getParameter("mobile"));*/
		String designation=request.getParameter("designation");

		Userbean user = new Userbean();
		Userbean user1 = new Userbean();

		
			if ((username != null) && (password != null)) {
				user.setUsername(username);
				user.setPassword(password);
				EmployeeLoginDao.insert(user);
				Boolean employeestatus = user.isValid();
				 System.out.println("status:::"+employeestatus);
				if (employeestatus) {
					String desig=user.getDesignation();
					if((desig.equals("softwaretrainee"))||(desig.equals("qualityanalyst"))||(desig.equals("softwaredeveloper"))||(desig.equals("hrdepartment"))) {
						
					
					HttpSession session = request.getSession(true);
					session.setAttribute("session1", user);
					response.sendRedirect("EmployeeIndex.jsp");
					}else {
						HttpSession session = request.getSession(true);
						
						session.setAttribute("session2", user);
						response.sendRedirect("Executiveindex.jsp");
					}
				} else {
					response.sendRedirect("LoginFailure.jsp");
				}
			} else {

				response.sendRedirect("LoginFailure.jsp");

			}
	
		} 
			

	
	
	}
		
		
		
		
		
		/* else if (name.equals("empreg")) {

			if ((name != null) && (username != null) && (password != null) && (confirmpassword != null)
					&& (domain != null) && (email != null) && (webmail != null) && (designation != null)) {

			
				
				user.setName(name);
				user.setDomain(domain);
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setConfirmpassword(confirmpassword);
				user.setEmail(email);
				user.setWebmail(webmail);
				user.setMobile(mobile);
				user.setDesignation(designation);
				RegistrationDao.insert(user);
				Boolean regstatus1 = user.isValid1();
				if (regstatus1) {
					Boolean regstatus = user.isValid();
					// System.out.println("adminstatus:::"+adminstatus);
					if (regstatus) {

						user1.setValid(true);
						
						try {
							Email1.mail(user);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (AddressException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						response.sendRedirect("Login.jsp");
					} else {
						user1.setValid(false);
						response.sendRedirect("Registration.jsp");
					}
				}
				else {

					response.sendRedirect("Registration.jsp");
				}
			} else {

				response.sendRedirect("Registration.jsp");
			}

		}else if (name.equals("exereg")) {

				if ((name != null) && (username != null) && (password != null) && (confirmpassword != null)
						&& (domain != null) && (email != null) && (webmail != null) && (designation != null)) {

				
					
					user.setName(name);
					user.setDomain(domain);
					user.setId(id);
					user.setUsername(username);
					user.setPassword(password);
					user.setConfirmpassword(confirmpassword);
					user.setEmail(email);
					user.setWebmail(webmail);
					user.setMobile(mobile);
					user.setDesignation(designation);
					RegistrationDao.insert(user);
					Boolean regstatus1 = user.isValid1();
					if (regstatus1) {
						Boolean regstatus = user.isValid();
						// System.out.println("adminstatus:::"+adminstatus);
						if (regstatus) {

							user1.setValid(true);
							
							try {
								Email1.mail(user);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (AddressException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							response.sendRedirect("Login.jsp");
						} else {
							user1.setValid(false);
							response.sendRedirect("Registration.jsp");
						}
					}
					else {

						response.sendRedirect("Registration.jsp");
					}
				} else {

					response.sendRedirect("Registration.jsp");
				}*/


	/*	} else if (name.equals("regProblem")) {

			if ((constitution != null) && (division != null) && (firstname != null) && (lastname != null)
					&& (email != null) && (gender != null) && (mobile != null) && (problemtype != null)
					&& (textarea != null)) {

				user.setConstitution(constitution);
				user.setDivision(division);
				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setGender(gender);
				user.setMobile(Long.parseLong(mobile));
				user.setProblemtype(problemtype);
				user.setTextarea(textarea);
				ProblemDao.insert(user);
				Boolean regstatus = user.isValid();
				// System.out.println("adminstatus:::"+adminstatus);
				if (regstatus) {

					response.sendRedirect("DivisionHome1.jsp");
				} else {
					response.sendRedirect("AddProblems1.jsp");
				}

			} else {
				response.sendRedirect("AddProblems.jsp");
			}
		} else if (name.equals("EditProblem")) {
			System.out.println(firstname);
			System.out.println(middlename);
			if ((firstname != null) && (lastname != null) && (mobile != null) && (email != null)
					&& (constitution != null) && (division != null) && (problemtype != null) && (textarea != null)) {

				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setMobile(Long.parseLong(mobile));

				user.setConstitution(constitution);
				user.setDivision(division);

				user.setProblemtype(problemtype);
				user.setTextarea(textarea);
				EditProblemDao.insert(user);
				Boolean regstatus = user.isValid();
				// System.out.println("adminstatus:::"+adminstatus);
				if (regstatus) {

					response.sendRedirect("ViewDivisionProblems1.jsp");
				} else {
					// System.out.println("null");
					response.sendRedirect("ViewDivisionProblems.jsp");
				}

			} else {
				// System.out.println("failed");
				response.sendRedirect("EditProblem.jsp");
			}
		} else if (name.equals("editconsprofile")) {

			if ((constitution != null) && (username != null) && (password != null) && (firstname != null)
					&& (lastname != null) && (email != null) && (mobile != null)) {

				user.setConstitution(constitution);
				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);

				user.setMobile(Long.parseLong(mobile));
				EditConstitutionDao.insert(user);
				Boolean regstatus = user.isValid();
				// System.out.println("adminstatus:::"+adminstatus);
				if (regstatus) {
					HttpSession session = request.getSession(true);
					session.setAttribute("ConstitutionSession", user);
					response.sendRedirect("ConstitutionHome1.jsp");
				} else {
					response.sendRedirect("ConstitutionProfile1.jsp");
				}
			} else {

				response.sendRedirect("ConstitutionProfile1.jsp");
			}

		} else if (name.equals("editdivprofile")) {

			if ((constitution != null) && (division != null) && (username != null) && (password != null)
					&& (firstname != null) && (lastname != null) && (email != null) && (mobile != null)) {

				user.setConstitution(constitution);
				user.setDivision(division);
				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);

				user.setMobile(Long.parseLong(mobile));
				EditDivisionDao.insert(user);
				Boolean regstatus = user.isValid();
				// System.out.println("adminstatus:::"+adminstatus);
				if (regstatus) {
					HttpSession session = request.getSession(true);
					session.setAttribute("DivisionSession", user);
					response.sendRedirect("DivisionHome2.jsp");
				} else {
					response.sendRedirect("DivisionProfile1.jsp");
				}
			} else {

				response.sendRedirect("DivisionProfile1.jsp");
			}
		} else if (name.equals("editadminprofile")) {

			if ((username != null) && (password != null) && (firstname != null) && (lastname != null) && (email != null)
					&& (mobile != null) && (id != null)) {

				user.setId(Integer.parseInt(id));
				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);

				user.setMobile(Long.parseLong(mobile));
				EditAdminDao.insert(user);
				Boolean regstatus = user.isValid();
				// System.out.println("adminstatus:::"+adminstatus);
				if (regstatus) {
					HttpSession session = request.getSession(true);
					session.setAttribute("AdminSession", user);
					response.sendRedirect("AdminHome1.jsp");
				} else {
					response.sendRedirect("AdminProfile1.jsp");
				}
			} else {

				response.sendRedirect("AdminProfile1.jsp");
			}
		}*/





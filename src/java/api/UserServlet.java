/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package api;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Fatec
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/users"})
public class UserServlet extends HttpServlet {

    
    private JSONObject getJSONBody(BufferedReader reader) throws Exception{
        StringBuilder buffer = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!= null){
            buffer.append(line);
        }
        return new JSONObject(buffer.toString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          processRequest(req, resp);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            JSONObject file = new JSONObject();
            try {
                if(request.getMethod().equals("PUT")){
                    //reading body
                    JSONObject body = getJSONBody(request.getReader());
                    //ading task, if exists
                    String nome = body.getString("nome");
                    String email = body.getString("email");
                    String senha = body.getString("senha");
                    
                    if (nome != null && email != null && senha != null) {
                        User u = new User(nome,senha,email);
                        User.users.add(u);
                    }
                    
                    file.put("list", new JSONArray(User.users));    
                }else if (request.getMethod().equals("GET")) {
                    file.put("list", new JSONArray(User.users));    
                }else if(request.getMethod().equals("DELETE")){
                    //DELETE don't support body
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");
                    int i = -1;
                    for (User u: User.users) {
                        if (u.getNome().equals(nome)|| u.getEmail().equals(email)||u.getSenha().equals(senha) ) {
                            i = User.users.indexOf(u);
                            break;
                        }
                        
                    }
                    if(i>=-1) User.users.remove(i);
                    file.put("list", new JSONArray(User.users));
                }else{
                    file.put("error", "invalid method"+request.getMethod());
                }
                response.getWriter().print(file.toString());
            } catch (Exception e) {
                file.put("error", e.getLocalizedMessage());
                response.getWriter().print(file.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

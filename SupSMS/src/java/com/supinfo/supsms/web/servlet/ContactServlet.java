/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.supinfo.supsms.web.servlet;

import com.supinfo.supsms.entity.Contact;
import com.supinfo.supsms.service.ContactService;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Clement
 */
@WebServlet(name = "ContactServlet", urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {
    
    @EJB
    private ContactService contactService;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Contact contact = contactService.findContactById(id);
        
        req.setAttribute("contact", contact);
        req.getRequestDispatcher("/jsp/contact.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Contact contact = contactService.findContactById(id);
        
        contact.setFirst_name(req.getParameter("user_first_name"));
        contact.setLast_name(req.getParameter("user_last_name"));
        contact.setEmail(req.getParameter("user_email"));
        
        String user_phone = req.getParameter("user_phone");
        Long user_phone_long = Long.valueOf(user_phone);
        contact.setPhone(user_phone_long);
        
        String user_zip = req.getParameter("user_zip");
        Long user_zip_long = Long.valueOf(user_zip);
        contact.setZip(user_zip_long);
        
        Date updated = new Date();
        contact.setUpdated(updated);
        
        contactService.updateContact(contact);
        
        resp.sendRedirect(req.getContextPath() + "/contact");
    }
    
    
    
}

package com.mes.controller;

import com.mes.util.HibernateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
    HashMap<String, Controller> router = new HashMap<String, Controller>();

    public FrontController() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        router.put("/login.do", new LoginController());
//        router.put("/admin/loginProc.do", new AdminLoginProcController());
    }

    public void destroy() {
        HibernateUtil.close();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = url.substring(contextPath.length());

        if (router.containsKey(path)) {
            Controller frontController = router.get(path);

            String method = request.getMethod();
            if (frontController.getMethod().equals(method)) {
                String pagePath = frontController.execute(request, response);
                if (pagePath != null) {
                    request.getRequestDispatcher(pagePath).forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/error/403.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/error/404.jsp").forward(request, response);
        }
    }
}

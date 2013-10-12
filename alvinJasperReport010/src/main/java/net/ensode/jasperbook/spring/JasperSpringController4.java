package net.ensode.jasperbook.spring;

import entity.Address;
import entity.School;
import entity.Userinfo;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JasperSpringController4 {

    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "/simpleReport4", method = RequestMethod.POST)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ModelAndView mav = new ModelAndView();

        //mock userinfo start
        Userinfo userinfo = new Userinfo();
        userinfo.setUsername("账号");
        userinfo.setPassword("密码");
        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            School school = new School();

            address.setId("id" + i);
            address.setAddressName("地址" + i);
            userinfo.getAddressList().add(address);

            school.setId("id" + i);
            school.setSchoolName("学校" + i);
            userinfo.getSchoolList().add(school);
        }
        //mock userinfo end

        mav.addObject("param_userinfo", userinfo);
        request.setAttribute("param_userinfo",userinfo);
        mav.setViewName("simpleReportX4");
        return mav;
    }
}

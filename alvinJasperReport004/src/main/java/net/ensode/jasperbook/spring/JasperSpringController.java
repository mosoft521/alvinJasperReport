package net.ensode.jasperbook.spring;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

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

public class JasperSpringController implements Controller {
    private DataSource dataSource;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        return new ModelAndView("simpleReport", getModel());
    }

    private Map getModel() throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        HashMap model = new HashMap();

        String query = "select id,name from t_id";

        connection = dataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);

        JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

        model.put("datasource", resultSetDataSource);
        return model;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}

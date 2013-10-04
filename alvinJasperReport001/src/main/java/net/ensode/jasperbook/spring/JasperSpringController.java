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
        return new ModelAndView("report", getModel());
    }

    private Map getModel() throws ClassNotFoundException, SQLException {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        HashMap model = new HashMap();

        String query = "select a.tail_num, a.aircraft_serial, "
                + "am.model as aircraft_model, ae.model as engine_model from aircraft a, "
                + "aircraft_models am, aircraft_engines ae where a.aircraft_engine_code in ("
                + "select aircraft_engine_code from aircraft_engines "
                + "where horsepower >= 1000) and am.aircraft_model_code = a.aircraft_model_code "
                + "and ae.aircraft_engine_code = a.aircraft_engine_code";

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

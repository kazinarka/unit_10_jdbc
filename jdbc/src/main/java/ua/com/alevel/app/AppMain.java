package ua.com.alevel.app;

import ua.com.alevel.app.dao.LocationDao;
import ua.com.alevel.app.dao.ProblemDao;
import ua.com.alevel.app.dao.RouteDao;
import ua.com.alevel.app.dao.SolutionDao;
import ua.com.alevel.app.dao.impl.LocationDaoImpl;
import ua.com.alevel.app.dao.impl.ProblemDaoImpl;
import ua.com.alevel.app.dao.impl.RouteDaoImpl;
import ua.com.alevel.app.dao.impl.SolutionDaoImpl;
import ua.com.alevel.app.service.FindingPathsService;
import ua.com.alevel.app.util.ConnectionUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class AppMain {

    public static void main(String[] args) {
        ConnectionUtils connectionUtils = new ConnectionUtils();
        try(Connection connection = connectionUtils.getConnection()){
            LocationDao locationDao = new LocationDaoImpl(connection);
            RouteDao routeDao = new RouteDaoImpl(connection);
            ProblemDao problemDao = new ProblemDaoImpl(connection);
            SolutionDao solutionDao = new SolutionDaoImpl(connection);
            new FindingPathsService(locationDao, routeDao, problemDao, solutionDao);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}

package ua.com.alevel.app.dao.impl;

import ua.com.alevel.app.dao.RouteDao;
import ua.com.alevel.app.data.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private final Connection connection;

    public RouteDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Route> read() {
        List<Route> routeList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM routes";
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Route route = new Route();
                route.setId(res.getInt(1));
                route.setFromId(res.getInt(2));
                route.setToId(res.getInt(3));
                route.setCost(res.getInt(4));
                routeList.add(route);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return routeList;
    }
}

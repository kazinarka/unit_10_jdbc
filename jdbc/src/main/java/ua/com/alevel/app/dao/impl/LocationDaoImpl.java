package ua.com.alevel.app.dao.impl;

import ua.com.alevel.app.dao.LocationDao;
import ua.com.alevel.app.data.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoImpl implements LocationDao {

    private final Connection connection;

    public LocationDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Location> read() {
        List<Location> locationList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM locations";
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Location location = new Location();
                location.setId(res.getInt(1));
                location.setName(res.getString(2));
                locationList.add(location);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return locationList;
    }
}

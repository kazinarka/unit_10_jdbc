package ua.com.alevel.app.dao.impl;

import ua.com.alevel.app.dao.SolutionDao;
import ua.com.alevel.app.data.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SolutionDaoImpl implements SolutionDao {

    private final Connection connection;

    public SolutionDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void create(List<Solution> solutions) {
        String sqlQuery = "INSERT INTO solutions (problem_id, cost) VALUES (?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            for(Solution solution : solutions){
                statement.setInt(1, solution.getProblemId());
                statement.setInt(2, solution.getCost());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }
}

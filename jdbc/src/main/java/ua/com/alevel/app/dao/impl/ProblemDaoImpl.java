package ua.com.alevel.app.dao.impl;

import ua.com.alevel.app.dao.ProblemDao;
import ua.com.alevel.app.data.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProblemDaoImpl implements ProblemDao {

    private final Connection connection;

    public ProblemDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Problem> read() {
        List<Problem> problemList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM problems p " +
                "LEFT JOIN solutions s on p.id = s.problem_id " +
                "WHERE s.problem_id IS NULL";
        try(PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet res = statement.executeQuery();
            while(res.next()){
                Problem problem = new Problem();
                problem.setId(res.getInt(1));
                problem.setFromId(res.getInt(2));
                problem.setToId(res.getInt(3));
                problemList.add(problem);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
        return problemList;
    }
}

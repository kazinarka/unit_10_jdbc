package ua.com.alevel.app.service;

import ua.com.alevel.app.dao.LocationDao;
import ua.com.alevel.app.dao.ProblemDao;
import ua.com.alevel.app.dao.RouteDao;
import ua.com.alevel.app.dao.SolutionDao;
import ua.com.alevel.app.data.Location;
import ua.com.alevel.app.data.Problem;
import ua.com.alevel.app.data.Route;
import ua.com.alevel.app.data.Solution;

import java.util.ArrayList;
import java.util.List;

public class FindingPathsService {

    public FindingPathsService(LocationDao locationDao, RouteDao routeDao, ProblemDao problemDao, SolutionDao solutionDao){
        List<Location> locations = locationDao.read();
        List<Route> routes = routeDao.read();
        List<Problem> problems = problemDao.read();
        List<Solution> solutions = findPaths(locations, routes, problems);
        solutionDao.create(solutions);
    }

    private List<Solution> findPaths(List<Location> locations, List<Route> routes, List<Problem> problems) {
        List<Solution> solutions = new ArrayList<>();
        int[][] matrix = new int[locations.size()][locations.size()];
        for (Route route : routes) {
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).getId() == route.getFromId()) {
                    fromIndex = i;
                } else if (locations.get(i).getId() == route.getToId()) {
                    toIndex = i;
                }
            }
            matrix[fromIndex][toIndex] = route.getCost();
        }
        for (Problem problem : problems) {
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).getId() == problem.getFromId()) {
                    fromIndex = i;
                } else if (locations.get(i).getId() == problem.getToId()) {
                    toIndex = i;
                }
            }
            int[] paths = dijkstra(matrix, fromIndex);
            solutions.add(new Solution(problem.getId(), paths[toIndex]));
        }
        return solutions;
    }

    private static int[] dijkstra(int [][] graph, int src) {
        int [] dist = new int[graph.length];
        Boolean [] sptSet = new Boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < graph.length - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < graph.length; v++)
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        return dist;
    }

    private static int minDistance(int[] dist, Boolean [] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++)
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }
}

package caiofurlan.serverdistributedsystems.system.utilities;

import caiofurlan.serverdistributedsystems.models.Model;
import caiofurlan.serverdistributedsystems.models.Point;
import caiofurlan.serverdistributedsystems.models.Segment;

import java.sql.SQLException;
import java.util.*;

public class RouteCalculator {

    private Map<Point, List<Segment>> adjacencyList = new HashMap<>();
    private Map<Point, Integer> distances = new HashMap<>();
    private Map<Point, Segment> previousSegments = new HashMap<>();
    private PriorityQueue<Point> queue;

    public RouteCalculator() throws SQLException {
        List<Segment> segments = Model.getInstance().getDatabaseDriver().getNotBlockedSegmentList();
        for (Segment segment : segments) {
            adjacencyList.putIfAbsent(segment.getPontoOrigem(), new ArrayList<>());
            adjacencyList.get(segment.getPontoOrigem()).add(segment);
        }
    }

    public List<Segment> calculateShortestPath(Point source, Point destination) {
        distances.put(source, 0);
        queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(source);

        while (!queue.isEmpty()) {
            Point currentPoint = queue.poll();
            for (Segment segment : adjacencyList.getOrDefault(currentPoint, Collections.emptyList())) {
                Point nextPoint = segment.getPontoDestino();
                int newDistance = distances.get(currentPoint) + segment.getDistancia();
                if (newDistance < distances.getOrDefault(nextPoint, Integer.MAX_VALUE)) {
                    distances.put(nextPoint, newDistance);
                    previousSegments.put(nextPoint, segment);
                    queue.add(nextPoint);
                }
            }
        }

        List<Segment> path = new ArrayList<>();
        for (Point point = destination; point != null; point = previousSegments.get(point).getPontoOrigem()) {
            path.add(previousSegments.get(point));
        }
        Collections.reverse(path);
        return path;
    }
}
import java.util.*;

class Solution {

    static int answer = 0;
    static Map<String, Map<Integer, Set<Integer>>> graph = new HashMap<>();

    public int solution(int[][] points, int[][] routes) {
        for (int i = 0; i < routes.length; i++) {
            int time = 0;
            
            for(int j=0; j<routes[i].length-1; j++) {
                int from = routes[i][j] - 1;
                int to = routes[i][j+1] - 1;
                int x1 = points[from][0];
                int y1 = points[from][1];
                int x2 = points[to][0];
                int y2 = points[to][1];
                
                // r좌표 이동
                    while (true) {
                        String graphIndex = String.valueOf(x1) + "," + String.valueOf(y1);
                        Map<Integer, Set<Integer>> map;

                        if (graph.containsKey(graphIndex)) {
                            map = graph.get(graphIndex);
                            Set<Integer> robots;

                            if (map.containsKey(time)) {
                                robots = map.get(time);
                            } else {
                                robots = new HashSet<>();
                            }

                            robots.add(i);
                            map.put(time, robots);
                        } else {
                            map = new HashMap<>();
                            Set<Integer> robots = new HashSet<>();
                            robots.add(i);
                            map.put(time, robots);
                        }

                        graph.put(graphIndex, map);

                        if (x1 == x2) {
                            break;
                        } else if (x1 < x2) {
                            x1 += 1;
                        } else {
                            x1 -= 1;
                        }

                        time += 1;
                    }

                    // c좌표 이동
                    while (true) {
                        String graphIndex = String.valueOf(x1) + "," + String.valueOf(y1);
                        Map<Integer, Set<Integer>> map;

                        if (graph.containsKey(graphIndex)) {
                            map = graph.get(graphIndex);
                            Set<Integer> robots;

                            if (map.containsKey(time)) {
                                robots = map.get(time);
                            } else {
                                robots = new HashSet<>();
                            }

                            robots.add(i);
                            map.put(time, robots);
                        } else {
                            map = new HashMap<>();
                            Set<Integer> robots = new HashSet<>();
                            robots.add(i);
                            map.put(time, robots);
                        }

                        graph.put(graphIndex, map);

                        if (y1 == y2) {
                            break;
                        } else if (y1 < y2) {
                            y1 += 1;
                        } else {
                            y1 -= 1;
                        }

                        time += 1;
                    }   
                }
            }
    
        for (String index : graph.keySet()) {
            Map<Integer, Set<Integer>> target = graph.get(index);

            for (Integer timeTarget : target.keySet()) {
                if (target.get(timeTarget).size() > 1) {
                    answer += 1;
                }
            }
        }
        
        return answer;
    }
}
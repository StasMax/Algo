import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Journey {

    public static class Node {

        private int x;
        private int y;

        private int countVertex = Integer.MAX_VALUE;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getCountVertex() {
            return countVertex;
        }

        public void setCountVertex(int countVertex) {
            this.countVertex = countVertex;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public Journey() {
        journey();
    }

    /**
     * Даны координаты и максимальное расстояние. Необходимо расчитать минимальное кол-во дорог из одной точки в другую
     * 1. Преставим координаты в виде вершин графа
     * 2. В моделе вершины кроме координат, создаем поле с кол-вом дорог до начальной точки с максимальным целочисленным значением по умолчанию
     * 3. В стартовой точке значение поля 0
     * 4. Пройдемся поиском в ширину по графу, заполняя поле в каждой точке, при первом совпадении выходим из цикла
     * 5. Возвращаем результат поля конечной точки
     */
    public static void journey() {
        String[] array = {"0 0", "0 2", "2 2", "0 -2", "2 -2", "2 -1", "2 1"};
        int startIndex = 1;
        int endIndex = 3;
        int maxLength = 2;

        Node startNode = null;
        Node finishNode = null;
        List<Node> nodeList = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            String[] xy = array[i].split(" ");
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if (i == startIndex) {
                startNode = new Node(x, y);
                startNode.setCountVertex(0);
                nodeList.add(0, startNode);
            } else if (i == endIndex) {
                finishNode = new Node(x, y);
                nodeList.add(finishNode);
            } else {
                nodeList.add(new Node(x, y));
            }
        }

        createEdgeAndWidthLoop(nodeList, startNode, maxLength, finishNode);

        System.out.println(finishNode.getCountVertex());
    }

    private static void createEdgeAndWidthLoop(List<Node> nodeList, Node startNode, int maxLength, Node endNode) {
        List<Node> currentNodeList = new ArrayList<>(nodeList);
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            currentNodeList.remove(currentNode);

            for (Node node : currentNodeList) {
                if ((currentNode.getX() == node.getX() && currentNode.getY() - node.getY() <= maxLength) ||
                        (currentNode.getX() - node.getX() <= maxLength && currentNode.getY() == node.getY())) {
                    node.setCountVertex(Math.min(node.getCountVertex(), currentNode.getCountVertex() + 1));
                    if (node == endNode) {
                        return;
                    }
                    queue.add(node);
                }
            }
        }
    }

}

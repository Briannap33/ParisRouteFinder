    package com.example._datastruc2_20102669;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.MouseButton;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.shape.Circle;
    import javafx.scene.shape.Line;
    import javafx.scene.shape.Rectangle;
    import javafx.scene.text.Text;
    import javafx.scene.layout.AnchorPane;
    import javafx.event.ActionEvent;
    import java.io.*;
    import java.util.*;
    import java.util.stream.Collectors;

    import javafx.scene.control.*;
    import javafx.scene.image.*;
    import javafx.scene.paint.Color;


    public class ParisController {

        public ImageView imageView = new ImageView();
        public List<GraphInfo<LandmarkInfo>> landmarkNodes = new ArrayList<>();
        public ListView<List<GraphInfo<?>>> listViewDepthFS = new ListView<>();
        public TextField maxNumDepthFSRoutes = new TextField();
        public ListView<GraphInfo<?>> waypointsListView = new ListView<>();
        public ChoiceBox<String> avoidPointChoiceBox;
        @FXML
        private ChoiceBox<String> startChoiceBox;
        @FXML
        private ChoiceBox<String> endChoiceBox;

        private List<GraphInfo<LandmarkInfo>> startAndEndNodesBreadthFS = new ArrayList<>();

        private List<GraphInfo<LandmarkInfo>> pixelPoints = new ArrayList<>();

        public void initialize() throws IOException {
            loadData();
            popChoiceBox();
            pointWithLabel();
            landmarkLinkAdd();
            bitmapProcess();


        }

        public void landmarkLinkAdd() {
            if (landmarkNodes.size() < 20) {
                System.out.println("Not enough landmark nodes to create links");
                return;
            }

            GraphInfo<LandmarkInfo> eiffelTower = landmarkNodes.get(0);
            GraphInfo<LandmarkInfo> louvre = landmarkNodes.get(1);
            GraphInfo<LandmarkInfo> notredame = landmarkNodes.get(2);
            GraphInfo<LandmarkInfo> arcdetriomphe = landmarkNodes.get(3);
            GraphInfo<LandmarkInfo> sacrecoeur = landmarkNodes.get(4);
            GraphInfo<LandmarkInfo> montmatre = landmarkNodes.get(5);
            GraphInfo<LandmarkInfo> musee = landmarkNodes.get(6);
            GraphInfo<LandmarkInfo> palais = landmarkNodes.get(7);
            GraphInfo<LandmarkInfo> champselysees = landmarkNodes.get(8);
            GraphInfo<LandmarkInfo> concorde = landmarkNodes.get(9);
            GraphInfo<LandmarkInfo> pantheon = landmarkNodes.get(10);
            GraphInfo<LandmarkInfo> lux = landmarkNodes.get(11);
            GraphInfo<LandmarkInfo> pompidou = landmarkNodes.get(12);
            GraphInfo<LandmarkInfo> saintechapelle = landmarkNodes.get(13);
            GraphInfo<LandmarkInfo> pontalexandre = landmarkNodes.get(14);
            GraphInfo<LandmarkInfo> vendome = landmarkNodes.get(15);
            GraphInfo<LandmarkInfo> a = landmarkNodes.get(16);
            GraphInfo<LandmarkInfo> b = landmarkNodes.get(17);
            GraphInfo<LandmarkInfo> c = landmarkNodes.get(18);
            GraphInfo<LandmarkInfo> d = landmarkNodes.get(19);

            eiffelTower.connectToNodeUndirected(musee, GraphAPI.calEdgeWeight(musee, eiffelTower));
            concorde.connectToNodeUndirected(vendome, GraphAPI.calEdgeWeight(vendome, concorde));
            notredame.connectToNodeUndirected(pompidou, GraphAPI.calEdgeWeight(pompidou, notredame));
            a.connectToNodeUndirected(musee, GraphAPI.calEdgeWeight(a, musee));
            a.connectToNodeUndirected(louvre, GraphAPI.calEdgeWeight(a, louvre));
            pontalexandre.connectToNodeUndirected(eiffelTower, GraphAPI.calEdgeWeight(pontalexandre, eiffelTower));
            eiffelTower.connectToNodeUndirected(arcdetriomphe, GraphAPI.calEdgeWeight(eiffelTower, arcdetriomphe));
            eiffelTower.connectToNodeUndirected(a, GraphAPI.calEdgeWeight(eiffelTower, a));
            arcdetriomphe.connectToNodeUndirected(champselysees, GraphAPI.calEdgeWeight(arcdetriomphe, champselysees));
            champselysees.connectToNodeUndirected(pontalexandre, GraphAPI.calEdgeWeight(champselysees, pontalexandre));
            pontalexandre.connectToNodeUndirected(concorde, GraphAPI.calEdgeWeight(pontalexandre, concorde));
            concorde.connectToNodeUndirected(musee, GraphAPI.calEdgeWeight(concorde, musee));
            musee.connectToNodeUndirected(vendome, GraphAPI.calEdgeWeight(musee, vendome));
            vendome.connectToNodeUndirected(louvre, GraphAPI.calEdgeWeight(vendome, louvre));
            louvre.connectToNodeUndirected(pompidou, GraphAPI.calEdgeWeight(louvre, pompidou));
            louvre.connectToNodeUndirected(saintechapelle, GraphAPI.calEdgeWeight(louvre, saintechapelle));
            saintechapelle.connectToNodeUndirected(pompidou, GraphAPI.calEdgeWeight(saintechapelle, pompidou));
            saintechapelle.connectToNodeUndirected(notredame, GraphAPI.calEdgeWeight(saintechapelle, notredame));
            notredame.connectToNodeUndirected(lux, GraphAPI.calEdgeWeight(notredame, lux));
            lux.connectToNodeUndirected(pantheon, GraphAPI.calEdgeWeight(lux, pantheon));
            notredame.connectToNodeUndirected(pantheon, GraphAPI.calEdgeWeight(notredame, pantheon));
            lux.connectToNodeUndirected(a, GraphAPI.calEdgeWeight(lux, a));
            a.connectToNodeUndirected(c, GraphAPI.calEdgeWeight(a, c));
            pontalexandre.connectToNodeUndirected(musee, GraphAPI.calEdgeWeight(pontalexandre, musee));
            c.connectToNodeUndirected(d, GraphAPI.calEdgeWeight(c, d));
            d.connectToNodeUndirected(pantheon, GraphAPI.calEdgeWeight(d, pantheon));
            b.connectToNodeUndirected(d, GraphAPI.calEdgeWeight(b, d));
            b.connectToNodeUndirected(pantheon, GraphAPI.calEdgeWeight(b, pantheon));
            palais.connectToNodeUndirected(sacrecoeur, GraphAPI.calEdgeWeight(palais, sacrecoeur));
            sacrecoeur.connectToNodeUndirected(montmatre, GraphAPI.calEdgeWeight(sacrecoeur, montmatre));
            palais.connectToNodeUndirected(champselysees, GraphAPI.calEdgeWeight(palais, champselysees));
            palais.connectToNodeUndirected(concorde, GraphAPI.calEdgeWeight(palais, concorde));
            palais.connectToNodeUndirected(pompidou, GraphAPI.calEdgeWeight(palais, pompidou));

        }


        public List<GraphInfo<?>> nearbyPoints(GraphInfo<?> nodeA) {
            List<GraphInfo<?>> nearbyNodes = new ArrayList<>();
            List<Map.Entry<GraphInfo<?>, Integer>> distanceEntries = new ArrayList<>();

            // Calculate distances and store them with their corresponding nodes
            for (GraphInfo<?> nodeB : this.pixelPoints) {
                if (nodeB != nodeA) {
                    int distance = GraphAPI.calEdgeWeight((GraphInfo<LandmarkInfo>) nodeB, (GraphInfo<LandmarkInfo>) nodeA);
                    distanceEntries.add(new AbstractMap.SimpleEntry<>(nodeB, distance));
                }
            }

            // Sort the entries by distance
            distanceEntries.sort(Comparator.comparingInt(Map.Entry::getValue));

            // Add the three closest nodes to the list
            for (int i = 0; i < 3 && i < distanceEntries.size(); i++) {
                nearbyNodes.add(distanceEntries.get(i).getKey());
            }

            return nearbyNodes;
        }

        // Returns 2-3 close/nearby nodes for the purpose of incorporating waypoint into LandmarkNodes list etc
        public List<GraphInfo<?>> nearbyLandmarkNodes(GraphInfo<?> nodeA) {
            List<GraphInfo<?>> nearbyNodes = new ArrayList<>();

            // Create a list to store nodes and their distances
            List<GraphInfo<?>> potentialNodes = new ArrayList<>();

            // Calculate distances and store nodes
            for (GraphInfo<?> nodeB : this.landmarkNodes) {
                if (nodeB != nodeA) {
                    potentialNodes.add(nodeB);
                }
            }

            // Sort nodes based on the distance to nodeA
            potentialNodes.sort(Comparator.comparingInt(node ->
                    GraphAPI.calEdgeWeight((GraphInfo<LandmarkInfo>) node, (GraphInfo<LandmarkInfo>) nodeA))
            );

            // Add the closest three nodes to nearbyNodes
            for (int i = 0; i < 3 && i < potentialNodes.size(); i++) {
                nearbyNodes.add(potentialNodes.get(i));
            }

            return nearbyNodes;
        }

        public void bitmapProcess() throws FileNotFoundException {
            Image bitmap = new Image(new FileInputStream("src/main/resources/bitmap-paris.bmp"));
            PixelReader pixelReader = bitmap.getPixelReader();
            WritableImage writableImage = new WritableImage((int) bitmap.getWidth(), (int) bitmap.getHeight());
            PixelWriter pixelWriter = writableImage.getPixelWriter();

            int imageWidth = (int) bitmap.getWidth();
            int imageHeight = (int) bitmap.getHeight();

            // Process pixels and populate pixelPoints
            for (int ycoord = 0; ycoord < imageHeight; ycoord++) {
                for (int xcoord = 0; xcoord < imageWidth; xcoord++) {
                    Color colorOfPixel = pixelReader.getColor(xcoord, ycoord);
                    boolean isRoadPixel = !colorOfPixel.equals(Color.BLACK);
                    pixelWriter.setColor(xcoord, ycoord, isRoadPixel ? Color.WHITE : Color.BLACK);

                    String nodeType = isRoadPixel ? "RoadPixel" : "B";
                    pixelPoints.add(new GraphInfo<>(new LandmarkInfo(nodeType, new PointPixel(xcoord, ycoord), 3)));
                }
            }

            //  Link road pixels to their neighbors
            for (int y = 0; y < imageHeight; y++) {
                for (int x = 0; x < imageWidth; x++) {
                    int indexOfPixel = y * imageWidth + x;

                    if (pixelPoints.get(indexOfPixel).nodeData.getName().equals("RoadPixel")) {
                        int rightIndex = indexOfPixel + 1;
                        int belowIndex = indexOfPixel + imageWidth;

                        roadConnectionPixel(indexOfPixel, rightIndex);
                        roadConnectionPixel(indexOfPixel, belowIndex);
                    }
                }
            }

            System.out.println("Number of pixel points in image: " + pixelPoints.size());
            // imageView.setImage(writableImage);
        }

        // Helper method to connect road pixels if they are adjacent
        private void roadConnectionPixel(int currentIndex, int neighborIndex) {
            if (neighborIndex < pixelPoints.size() &&
                    pixelPoints.get(neighborIndex).nodeData.getName().equals("RoadPixel")) {
                pixelPoints.get(currentIndex).connectToNodeUndirected(
                        pixelPoints.get(neighborIndex),
                        GraphAPI.calEdgeWeight(pixelPoints.get(currentIndex), pixelPoints.get(neighborIndex))
                );
            }
        }

        public void mapReset() {
            AnchorPane anchorPane = (AnchorPane) imageView.getParent();
            anchorPane.getChildren().removeIf(component -> component instanceof Line);
            anchorPane.getChildren().removeIf(component -> component instanceof Circle);
            landmarkNodes.clear(); // Reset landmark node list and re-add them so that all temporary waypoints added are removed
            listViewDepthFS.getItems().clear();
            waypointsListView.getItems().clear();
            maxNumDepthFSRoutes.clear();
            startAndEndNodesBreadthFS.clear();

            try {
                loadData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Connect each node again as the list was reset
            landmarkLinkAdd();

        }

        /* loading in data from csv */
        public void loadData() throws IOException {
            String filePath = "src/main/resources/nodes.csv";

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] line = row.split(",");
                    if (line.length >= 4) {
                        try {
                            String name = line[0];
                            PointPixel point = new PointPixel(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                            int someValue = Integer.parseInt(line[3]);

                            LandmarkInfo landmark = new LandmarkInfo(name, point, someValue);
                            GraphInfo<LandmarkInfo> graphNode = new GraphInfo<>(landmark);
                            this.landmarkNodes.add(graphNode);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number format in row: " + row);
                        }
                    } else {
                        System.err.println("Skipping malformed row: " + row);
                    }
                }

                System.out.println("Successfully loaded data.");
                System.out.println("Total number of nodes: " + landmarkNodes.size());
            } catch (IOException e) {
                System.err.println("Error occurred when loading data: " + e.getMessage());
                throw e;  // Re-throw the exception if it should be handled by the caller
            }
        }

        public void pointWithLabel() {
            AnchorPane parentPane = (AnchorPane) imageView.getParent();
            double imageViewLayoutX = imageView.getLayoutX();
            double imageViewLayoutY = imageView.getLayoutY();

            for (GraphInfo<LandmarkInfo> node : this.landmarkNodes) {
                // Create and configure the rectangle
                Rectangle rect = new Rectangle(node.nodeData.getxCoord(), node.nodeData.getyCoord(), 7, 7);
                rect.setFill(Color.BLACK);
                rect.setLayoutX(imageViewLayoutX);
                rect.setLayoutY(imageViewLayoutY);

                // Create and configure the label
                Text label = new Text(node.nodeData.getName());
                label.setLayoutX(imageViewLayoutX);
                label.setLayoutY(imageViewLayoutY);
                label.setX(rect.getX() - 5);
                label.setY(rect.getY() - 2);

                // Add rectangle and label to the parent pane
                parentPane.getChildren().addAll(rect, label);
            }
        }

        private void popChoiceBox() {
            // Create a single ObservableList for landmark names
            ObservableList<String> landmarkNames = FXCollections.observableArrayList(
                    landmarkNodes.stream()
                            .map(node -> node.nodeData.getName())
                            .collect(Collectors.toList())
            );

            // Set the items for each ChoiceBox
            startChoiceBox.setItems(landmarkNames);
            endChoiceBox.setItems(landmarkNames);
            avoidPointChoiceBox.setItems(landmarkNames);
        }

        public void landmarkNodeLines(MouseEvent event) {
            if (event.getButton() != MouseButton.PRIMARY || event.getClickCount() != 1) {
                return; // Exit if the event is not a single left click
            }

            AnchorPane anchorPane = (AnchorPane) imageView.getParent();
            anchorPane.getChildren().removeIf(component -> component instanceof Line);

            List<GraphInfo<?>> pathList = listViewDepthFS.getSelectionModel().getSelectedItem();

            if (pathList == null || pathList.size() < 2) {
                return; // No lines to draw if the list is null or has fewer than two nodes
            }

            for (int i = 0; i < pathList.size() - 1; i++) {
                GraphInfo<LandmarkInfo> nodeA = (GraphInfo<LandmarkInfo>) pathList.get(i);
                GraphInfo<LandmarkInfo> nodeB = (GraphInfo<LandmarkInfo>) pathList.get(i + 1);

                // Create and configure the line
                Line line = new Line(nodeA.nodeData.getxCoord(), nodeA.nodeData.getyCoord(), nodeB.nodeData.getxCoord(), nodeB.nodeData.getyCoord());
                line.setStroke(Color.PEACHPUFF);
                line.setStrokeWidth(4);
                line.setLayoutY(imageView.getLayoutY());
                line.setLayoutX(imageView.getLayoutX());

                // Add the line to the anchor pane
                anchorPane.getChildren().add(line);
            }
        }

        public void genDFSRoutes(ActionEvent actionEvent) {
            listViewDepthFS.getItems().clear();

            String startingNodeName = startChoiceBox.getValue();
            String destNodeName = endChoiceBox.getValue();

            // Find startNode and destNode
            GraphInfo<LandmarkInfo> startNode = null;
            GraphInfo<LandmarkInfo> destNode = nodeByName(destNodeName);

            for (GraphInfo<LandmarkInfo> node : landmarkNodes) {
                if (node.nodeData.getName().equals(startingNodeName)) {
                    startNode = node;
                }
                // Directly set destNode if it matches destNodeName
                if (node.nodeData.getName().equals(destNodeName)) {
                    destNode = node;
                }
            }
            // Check if startNode was found, otherwise exit
            if (startNode == null || destNode == null) {
                return;
            }

            // Find all paths using Depth First Search
            List<List<GraphInfo<?>>> listOfPaths = GraphAPI.depthFirstAllPaths(startNode, null, destNode.nodeData);

            // Add paths to ListView with a limit
            int maxPaths = maxNumberRoutesDFS();
            for (int i = 0; i < listOfPaths.size() && i < maxPaths; i++) {
                listViewDepthFS.getItems().add(listOfPaths.get(i));
            }
        }

        private int maxNumberRoutesDFS() {
            String text = maxNumDepthFSRoutes.getText();
            return text.isEmpty() ? 1 : Integer.parseInt(text);
        }

        @FXML
        private void dijkstraShortPath(ActionEvent event) {
            // Get the parent AnchorPane of the imageView and remove any existing lines (paths) drawn on it
            AnchorPane anchorPane = (AnchorPane) imageView.getParent();
            anchorPane.getChildren().removeIf(component -> component instanceof Line);

            // Retrieve the names of the start and end landmarks selected by the user
            String startLandmarkName = startChoiceBox.getValue();
            String endLandmarkName = endChoiceBox.getValue();

            // Find the corresponding nodes in the graph for the start and end landmarks
            GraphInfo<LandmarkInfo> startNode = nodeByName(startLandmarkName);
            GraphInfo<LandmarkInfo> endNode = nodeByName(endLandmarkName);

            List<GraphInfo<LandmarkInfo>> shortestPath = GraphAPI.dijkstraLightestPath(startNode, endNode, landmarkNodes);

            // If there are no waypoints selected, visualize the direct shortest path and return
            if (waypointsListView.getItems().isEmpty()) {
                pathMapVisual(shortestPath, Color.RED);
                return;
            }

            // Retrieve the list of waypoints selected by the user
            List<GraphInfo<LandmarkInfo>> waypoints = getWaypoints();

            // Build paths through the waypoints from the start node to the end node
            List<List<GraphInfo<LandmarkInfo>>> waypointPaths = buildPathWaypoint(startNode, endNode, waypoints);

            for (List<GraphInfo<LandmarkInfo>> path : waypointPaths) {
                pathMapVisual(path, Color.MEDIUMPURPLE);
            }
        }

        private List<GraphInfo<LandmarkInfo>> getWaypoints() {
            List<GraphInfo<LandmarkInfo>> waypoints = new ArrayList<>();
            for (GraphInfo<LandmarkInfo> node : landmarkNodes) {
                if ("WayPoint".equals(node.nodeData.getName())) {
                    waypoints.add(node);
                }
            }
            System.out.println("Waypoints added to their own list temporarily\n");
            System.out.println(waypoints.size());
            return waypoints;
        }

        private List<List<GraphInfo<LandmarkInfo>>> buildPathWaypoint(GraphInfo<LandmarkInfo> startNode, GraphInfo<LandmarkInfo> endNode, List<GraphInfo<LandmarkInfo>> waypoints) {
            List<List<GraphInfo<LandmarkInfo>>> waypointPaths = new ArrayList<>();
            // Path from startNode to the first waypoint
            waypointPaths.add(GraphAPI.dijkstraLightestPath(startNode, waypoints.get(0), landmarkNodes));
            // Paths between consecutive waypoints
            for (int i = 0; i < waypoints.size() - 1; i++) {
                waypointPaths.add(GraphAPI.dijkstraLightestPath(waypoints.get(i), waypoints.get(i + 1), landmarkNodes));
            }
            // Path from the last waypoint to endNode
            waypointPaths.add(GraphAPI.dijkstraLightestPath(waypoints.get(waypoints.size() - 1), endNode, landmarkNodes));

            return waypointPaths;
        }

        public void genBFSShortestPath(ActionEvent event) {
            // Retrieve start and end nodes
            GraphInfo<LandmarkInfo> startNode = startAndEndNodesBreadthFS.get(0);
            GraphInfo<LandmarkInfo> endNode = startAndEndNodesBreadthFS.get(1);

            // Find the shortest path using BFS
            List<GraphInfo<LandmarkInfo>> path = GraphAPI.shortBFSPath(startNode, endNode);

            pathMapVisual(path, Color.HOTPINK);
        }

        @FXML
        public void BFSShortestPath(int x, int y) {
            if (startAndEndNodesBreadthFS.size() < 3) {
                // Create and style the marker circle
                Circle marker = new Circle(x, y, 3);
                marker.setLayoutX(imageView.getLayoutX());
                marker.setLayoutY(imageView.getLayoutY());
                marker.setFill(Color.BLUEVIOLET);
                marker.setStrokeWidth(3.5);
                marker.setStroke(Color.DARKBLUE);

                // Add marker to the drawing area
                ((AnchorPane) imageView.getParent()).getChildren().add(marker);

                GraphInfo<LandmarkInfo> newNode = new GraphInfo<>(new LandmarkInfo("RoadPixel", new PointPixel(x, y), 3));

                pixelPoints.add(newNode);
                startAndEndNodesBreadthFS.add(newNode);

                // Find nearby road nodes and connect them
                for (GraphInfo<?> nearbyNode : nearbyPoints(newNode)) {
                    if (nearbyNode != null) {
                        int cost = GraphAPI.calEdgeWeight(newNode, (GraphInfo<LandmarkInfo>) nearbyNode);
                        newNode.connectToNodeUndirected((GraphInfo<LandmarkInfo>) nearbyNode, cost);
                    }
                }

                // Ensure the newNode is updated in pixelPoints
                if (pixelPoints.remove(newNode)) {
                    pixelPoints.add(newNode);
                }

                List<GraphInfo<?>> nearbyNodes = nearbyPoints(newNode);
                System.out.println("Number of nearby nodes: " + nearbyNodes.size());
                System.out.println(nearbyNodes);
            }
        }


        private GraphInfo<LandmarkInfo> nodeByName(String name) {
            return landmarkNodes.stream()
                    .filter(node -> node.nodeData.getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null); // Node not found
        }

        private void pathMapVisual(List<GraphInfo<LandmarkInfo>> path, Color color) {
            if (path == null || path.isEmpty()) {
                System.out.println("Path is empty");
                return;
            }

            double imageViewX = imageView.getLayoutX();
            double imageViewY = imageView.getLayoutY();
            AnchorPane drawingArea = (AnchorPane) imageView.getParent(); // Get drawing area once

            // Iterate through the path and draw lines between consecutive nodes
            for (int i = 0; i < path.size() - 1; i++) {
                GraphInfo<LandmarkInfo> currentNode = path.get(i);
                GraphInfo<LandmarkInfo> nextNode = path.get(i + 1);

                // Create a line with adjusted start and end positions
                Line line = new Line(
                        imageViewX + currentNode.nodeData.getxCoord(),
                        imageViewY + currentNode.nodeData.getyCoord(),
                        imageViewX + nextNode.nodeData.getxCoord(),
                        imageViewY + nextNode.nodeData.getyCoord()
                );
                line.setStroke(color);
                line.setStrokeWidth(2);

                drawingArea.getChildren().add(line); // Add the line to the drawing area
            }
        }

        public void addWayPoint(MouseEvent event) {
            if (event.getClickCount() == 1 && event.getButton() == MouseButton.MIDDLE) {
                // Handle middle mouse button click
                BFSShortestPath((int) event.getX(), (int) event.getY());
                return;
            }

            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                // Create and configure the circle dot
                Circle dot = new Circle(event.getX(), event.getY(), 4);
                dot.setLayoutX(imageView.getLayoutX());
                dot.setLayoutY(imageView.getLayoutY());
                dot.setFill(Color.LIGHTCORAL);
                dot.setStrokeWidth(3.5);
                dot.setStroke(Color.WHITE);

                // Add the circle to the AnchorPane
                AnchorPane drawingArea = (AnchorPane) imageView.getParent();
                drawingArea.getChildren().add(dot);

                // Create a new waypoint
                PointPixel pointPixel = new PointPixel((int) event.getX(), (int) event.getY());
                int histValue = (pointPixel.getxCoord() > 221 && pointPixel.getxCoord() <= 521 &&
                        pointPixel.getyCoord() > 174 && pointPixel.getyCoord() <= 396) ? 55 : 3;
                LandmarkInfo newNode = new LandmarkInfo("WayPoint", pointPixel, histValue);
                GraphInfo<LandmarkInfo> waypointNode = new GraphInfo<>(newNode);

                // Add waypoint to list view and landmark nodes
                waypointsListView.getItems().add(waypointNode);
                landmarkNodes.add(waypointNode);

                // Find and connect to nearby nodes
                List<GraphInfo<?>> nearbyNodes = nearbyLandmarkNodes(waypointNode);
                for (GraphInfo<?> node : nearbyNodes) {
                    waypointNode.connectToNodeUndirected((GraphInfo<LandmarkInfo>) node,
                            GraphAPI.calEdgeWeight(waypointNode, (GraphInfo<LandmarkInfo>) node));
                }

                if (landmarkNodes.remove(waypointNode)) {
                    System.out.println("Waypoint has been removed");
                }
                landmarkNodes.add(waypointNode);
                System.out.println("Added Waypoint again!");
            }
        }

        public void pathPointToAvoid(ActionEvent actionEvent) {
            // Find the node to avoid
            GraphInfo<LandmarkInfo> nodeToAvoid = nodeByName(avoidPointChoiceBox.getValue());

            if (nodeToAvoid == null) {
                return;
            }

            // Create a set of node names to avoid for quick lookup
            String nodeToAvoidName = nodeToAvoid.nodeData.getName();

            // Iterate over all nodes to remove edges to the node to avoid
            for (GraphInfo<LandmarkInfo> node : landmarkNodes) {
                node.neighbour.removeIf(adjacentNode ->
                        adjacentNode.destinNode.nodeData.getName().equals(nodeToAvoidName)
                );
            }

            landmarkNodes.remove(nodeToAvoid);

            System.out.println("Removed " + nodeToAvoidName);
        }

        public void genMostHistoricalRoute(ActionEvent actionEvent) {
            // Retrieve start and end node names from the UI
            String startNodeName = startChoiceBox.getValue();
            String endNodeName = endChoiceBox.getValue();

            // Find corresponding nodes in the graph
            GraphInfo<LandmarkInfo> startNode = nodeByName(startNodeName);
            GraphInfo<LandmarkInfo> destNode = nodeByName(endNodeName);

            // Check if either node was not found
            if (startNode == null || destNode == null) {
                displayMessage("Error: One or both nodes not found.");
                return;
            }

            // Compute the most historical path between the nodes
            List<GraphInfo<LandmarkInfo>> mostHistoricalPath = GraphAPI.dijkstraHistoricPath(startNode, destNode, landmarkNodes);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("~~~~~~~ The Most Historical Path ~~~~~~~\n");
            for (GraphInfo<LandmarkInfo> pathNode : mostHistoricalPath) {
                resultBuilder.append(pathNode.nodeData.getName())
                        .append(": ")
                        .append(pathNode.nodeData.getCultureVal())
                        .append("\n");
            }
            resultBuilder.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            // Display the result message
            displayMessage(resultBuilder.toString());
        }

        // Placeholder method to displaying messages
        private void displayMessage(String message) {
            System.out.println(message);
        }

        public void displayLinksWithLines(ActionEvent actionEvent) {
            // Iterate over each node in landmarkNodes
            for (GraphInfo<LandmarkInfo> node : landmarkNodes) {
                // Iterate over each link in the adjacency list of the current node
                node.neighbour.forEach(link -> {
                    GraphInfo<LandmarkInfo> destNode = link.destinNode;

                    // Create a path with the current node and destination node
                    List<GraphInfo<LandmarkInfo>> miniPath = Arrays.asList(node, destNode);

                    // Visualize the path on the map
                    pathMapVisual(miniPath, Color.HOTPINK);
                });
            }
        }
    }
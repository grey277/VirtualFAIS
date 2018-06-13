package com.grey.virtualfais;

import com.grey.virtualfais.models.Level;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.Map;

import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterGraph;
import es.usc.citius.hipster.model.problem.SearchProblem;

class Node
{
    Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    private final int x;
    private final int y;
}

class MapNodes
{
    MapNodes()
    {
        groundFloor = new HashMap<>();
        firstFloor = new HashMap<>();
        secondFloor = new HashMap<>();
        fillGroundFloorNodes();
        fillFirstFloorNodes();
        fillSecondFloorNodes();
    }

    void fillGroundFloorNodes()
    {
        groundFloor.put("START", new Node(8524, 4816));
        groundFloor.put("A01", new Node(8744, 4092));
        groundFloor.put("A02", new Node(9159, 4005));
        groundFloor.put("A03", new Node(9333, 3474));
        groundFloor.put("A04", new Node(7567, 3880));
        groundFloor.put("A05", new Node(7792, 5032));
        groundFloor.put("A06", new Node(7680, 5472));
        groundFloor.put("H01", new Node(9343, 2750));
        groundFloor.put("H02", new Node(9343, 1090));
        groundFloor.put("G01", new Node(9028, 812));
        groundFloor.put("G02", new Node(6456, 820));
        groundFloor.put("F01", new Node(5852, 808));
        groundFloor.put("F02", new Node(5496, 840));
        groundFloor.put("F03", new Node(4636, 2324));
        groundFloor.put("F04", new Node(5900, 2980));
        groundFloor.put("E01", new Node(7229, 3551));
        groundFloor.put("E02", new Node(6891, 3687));
        groundFloor.put("E03", new Node(6476, 3532));
        groundFloor.put("E04", new Node(6370, 3185));
        groundFloor.put("E05", new Node(7476, 3796));
        groundFloor.put("OS1", new Node(6816, 3888));
        groundFloor.put("OS2", new Node(3980, 2304));
        groundFloor.put("OS3", new Node(4036, 1700));
        groundFloor.put("OS4", new Node(6944, 5676));
        groundFloor.put("J01", new Node(3812, 1448));
        groundFloor.put("J02", new Node(3648, 1336));
        groundFloor.put("B01", new Node(3660, 5676));
        groundFloor.put("B02", new Node(5652, 5664));
        groundFloor.put("B03", new Node(5656, 4484));
        groundFloor.put("B04", new Node(4652, 3892));
        groundFloor.put("B05", new Node(5972, 5664));
        groundFloor.put("C01", new Node(4200, 3640));
        groundFloor.put("C02", new Node(3956, 3488));
        groundFloor.put("C03", new Node(3708, 3332));
        groundFloor.put("C04", new Node(2720, 5668));
        groundFloor.put("D01", new Node(3244, 3096));
        groundFloor.put("D02", new Node(2472, 2648));
        groundFloor.put("D03", new Node(1340, 4604));
        groundFloor.put("D04", new Node(756, 5648));
        groundFloor.put("D05", new Node(1756, 5672));
        groundFloor.put("K01", new Node(3364, 5652));
        groundFloor.put("K02", new Node(4440, 3768));
    }

    void fillFirstFloorNodes()
    {

    }

    void fillSecondFloorNodes()
    {
        secondFloor.put("START", new Node(7980, 3580));
        secondFloor.put("A21", new Node(7130, 5633));
        secondFloor.put("A22", new Node(7521, 4403));
        secondFloor.put("A23", new Node(7636, 3850));
        secondFloor.put("A24", new Node(8443, 3606));
        secondFloor.put("A25", new Node(9433, 3353));

        secondFloor.put("B21", new Node(3657, 5656));
        secondFloor.put("B22", new Node(5670, 5610));
        secondFloor.put("B23", new Node(5660, 4449));
        secondFloor.put("B24", new Node(4670, 3859));

        secondFloor.put("C21", new Node(4081, 3145));
        secondFloor.put("C22", new Node(3933, 3422));
        secondFloor.put("C23", new Node(2625, 5679));

        secondFloor.put("D21", new Node(3215, 3021));
        secondFloor.put("D22", new Node(2418, 2570));
        secondFloor.put("D23", new Node(1243, 4596));
        secondFloor.put("D24", new Node(690, 5619));
        secondFloor.put("D25", new Node(1648, 5656));

        secondFloor.put("F21", new Node(5596, 594));
        secondFloor.put("F22", new Node(4629, 2215));

        secondFloor.put("G21", new Node(9110, 686));
        secondFloor.put("G22", new Node(6485, 663));

        secondFloor.put("H21", new Node(9456, 2630));
        secondFloor.put("H22", new Node(9479, 994));


        secondFloor.put("K21", new Node(9318, 810));
        secondFloor.put("K22", new Node(6199, 649));
        secondFloor.put("K23", new Node(4338, 3675));
        secondFloor.put("K24", new Node(3256, 5619));

    }

    Map<String, Node> getGroundFloor()
    {
        return groundFloor;
    }

    Map<String, Node> getFirstFloor()
    {
        return firstFloor;
    }

    Map<String, Node> getSecondFloor()
    {
        return secondFloor;
    }

    private Map<String, Node> groundFloor;
    private Map<String, Node> firstFloor;
    private Map<String, Node> secondFloor;
}

public class PathFinder
{
    PathFinder()
    {
        mapNodes = new MapNodes();
    }

    private int getDistanceBetweenPoints(Node firstNode, Node secondNode)
    {
        return (int)  Math.sqrt(Math.pow(firstNode.getX() - secondNode.getX(), 2)
                    + Math.pow(firstNode.getY() - secondNode.getY(), 2));
    }

    public Node getNodeFromLevelMapNode(String nodeName, Level level) {
        switch (level) {
            case ZERO:
                return getNodeFromGroundFloorMapNodes(nodeName);
            case ONE:
                return getNodeFromFirstFloorMapNodes(nodeName);
            case TWO:
                return getNodeFromSecondFloorMapNodes(nodeName);
            default:
                throw new IllegalArgumentException("Unknown level");
        }
    }

    Node getNodeFromGroundFloorMapNodes(String nodeName)
    {
        return mapNodes.getGroundFloor().get(nodeName);
    }

    Node getNodeFromFirstFloorMapNodes(String nodeName)
    {
        return mapNodes.getFirstFloor().get(nodeName);
    }

    Node getNodeFromSecondFloorMapNodes(String nodeName)
    {
        return mapNodes.getSecondFloor().get(nodeName);
    }

    private Character getSegmentFromRoomId(String roomId)
    {
            return roomId.charAt(0);
    }

    private Character getFloorFromRoomId(String roomId)
    {
            return roomId.charAt(2);
    }

    private GraphBuilder<String, Integer> setPathBetween(GraphBuilder<String, Integer> graphBuilder, Map<String, Node> nodes, String a, String b) {
        return graphBuilder.connect(a).to(b).withEdge(getDistanceBetweenPoints(nodes.get(a), nodes.get(b)));
    }

    private HipsterGraph<String, Integer> getGroundFloorGraph()
    {
        return GraphBuilder.<String, Integer>create()
                .connect("START").to("A01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("START"), getNodeFromGroundFloorMapNodes("A01")))
                .connect("START").to("A04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("START"), getNodeFromGroundFloorMapNodes("A04")))
                .connect("START").to("A05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("START"), getNodeFromGroundFloorMapNodes("A05")))
                .connect("A01").to("A02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("A01"), getNodeFromGroundFloorMapNodes("A02")))
                .connect("A02").to("A03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("A02"), getNodeFromGroundFloorMapNodes("A03")))
                .connect("A05").to("A06").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("A05"), getNodeFromGroundFloorMapNodes("A06")))
                .connect("A04").to("E05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("A04"), getNodeFromGroundFloorMapNodes("E05")))
                .connect("A03").to("H01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("A03"), getNodeFromGroundFloorMapNodes("H01")))
                .connect("H01").to("H02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("H01"), getNodeFromGroundFloorMapNodes("H02")))
                .connect("H02").to("G01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("H02"), getNodeFromGroundFloorMapNodes("G01")))
                .connect("G01").to("G02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("G01"), getNodeFromGroundFloorMapNodes("G02")))
                .connect("G02").to("F01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("G02"), getNodeFromGroundFloorMapNodes("F01")))
                .connect("F01").to("F02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("F01"), getNodeFromGroundFloorMapNodes("F02")))
                .connect("F02").to("F03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("F02"), getNodeFromGroundFloorMapNodes("F03")))
                .connect("F03").to("F04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("F03"), getNodeFromGroundFloorMapNodes("F04")))
                .connect("F04").to("E04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("F04"), getNodeFromGroundFloorMapNodes("E04")))
                .connect("E05").to("B05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E05"), getNodeFromGroundFloorMapNodes("B05")))
                .connect("E04").to("E03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E04"), getNodeFromGroundFloorMapNodes("E03")))
                .connect("E03").to("E02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E03"), getNodeFromGroundFloorMapNodes("E02")))
                .connect("E02").to("E01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E02"), getNodeFromGroundFloorMapNodes("E01")))
                .connect("E01").to("E05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E01"), getNodeFromGroundFloorMapNodes("E05")))
                .connect("E05").to("OS1").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("E05"), getNodeFromGroundFloorMapNodes("OS1")))
                .connect("OS1").to("OS4").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("OS1"), getNodeFromGroundFloorMapNodes("OS4")))
                .connect("OS1").to("OS2").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("OS1"), getNodeFromGroundFloorMapNodes("OS2")))
                .connect("OS2").to("OS3").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("OS2"), getNodeFromGroundFloorMapNodes("OS3")))
                .connect("OS3").to("J01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("OS3"), getNodeFromGroundFloorMapNodes("J01")))
                .connect("J01").to("J02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("J01"), getNodeFromGroundFloorMapNodes("J02")))
                .connect("OS4").to("B05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("OS4"), getNodeFromGroundFloorMapNodes("B05")))
                .connect("B05").to("B02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B05"), getNodeFromGroundFloorMapNodes("B02")))
                .connect("B02").to("B01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B02"), getNodeFromGroundFloorMapNodes("B01")))
                .connect("B02").to("B03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B02"), getNodeFromGroundFloorMapNodes("B03")))
                .connect("B03").to("B04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B03"), getNodeFromGroundFloorMapNodes("B04")))
                .connect("B04").to("K02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B04"), getNodeFromGroundFloorMapNodes("K02")))
                .connect("B01").to("K01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("B01"), getNodeFromGroundFloorMapNodes("K01")))
                .connect("K02").to("C01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("K02"), getNodeFromGroundFloorMapNodes("C01")))
                .connect("C01").to("C02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("C01"), getNodeFromGroundFloorMapNodes("C02")))
                .connect("C02").to("C03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("C02"), getNodeFromGroundFloorMapNodes("C03")))
                .connect("C02").to("C04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("C02"), getNodeFromGroundFloorMapNodes("C04")))
                .connect("C03").to("D01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("C03"), getNodeFromGroundFloorMapNodes("D01")))
                .connect("C04").to("K01").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("C04"), getNodeFromGroundFloorMapNodes("K01")))
                .connect("D01").to("D02").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("D01"), getNodeFromGroundFloorMapNodes("D02")))
                .connect("D02").to("D03").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("D02"), getNodeFromGroundFloorMapNodes("D03")))
                .connect("D03").to("D04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("D03"), getNodeFromGroundFloorMapNodes("D04")))
                .connect("D04").to("D05").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("D04"), getNodeFromGroundFloorMapNodes("D05")))
                .connect("D05").to("C04").withEdge(getDistanceBetweenPoints(getNodeFromGroundFloorMapNodes("D05"), getNodeFromGroundFloorMapNodes("C04")))
                .createUndirectedGraph();
    }

    private HipsterGraph<String, Integer> getFirstFloorGraph()
    {
        return getGroundFloorGraph();
    }

    private HipsterGraph<String, Integer> getSecondFloorGraph()
    {
        GraphBuilder<String, Integer> graphBuilder = GraphBuilder.create();
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "START", "A23");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A23", "A22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A23", "A24");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A24", "A25");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A22", "A21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A21", "B22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "B22", "B23");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "B22", "B21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "B23", "B24");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "B24", "K23");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "B21", "K24");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "K24", "C23");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "C23", "C22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "C23", "D25");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "C22", "K23");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "C22", "C21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "C21", "F22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "D25", "D24");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "D23", "D24");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "D23", "D22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "D21", "D22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "D21", "C22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "F22", "F21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "K22", "F21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "K22", "G22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "G21", "G22");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "G21", "K21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "H22", "K21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "H22", "H21");
        setPathBetween(graphBuilder, mapNodes.getSecondFloor(), "A25", "H21");


        return graphBuilder.createUndirectedGraph();
    }

    public LinkedList<String> getPathToPoint(String roomId)
    {
        Character floorId = getFloorFromRoomId(roomId);

        HipsterGraph<String, Integer> graph;
        switch (floorId)
        {
            case '0':
                graph = getGroundFloorGraph();
            break;
            case '1':
                graph = getFirstFloorGraph();
            break;
            case '2':
                graph = getSecondFloorGraph();
            break;
            default:
                graph = getGroundFloorGraph();
            break;
        }

        SearchProblem p = GraphSearchProblem
                .startingFrom("START")
                .in(graph)
                .takeCostsFromEdges()
                .build();

        String floorIdWithFirstRoom = floorId + "1";
        String segmentId = getSegmentFromRoomId(roomId) + floorIdWithFirstRoom; //TODO: change it later, temporary solution
        return (LinkedList<String>) Hipster.createAStar(p).search(segmentId).getOptimalPaths().get(0);
    }

    private MapNodes mapNodes;
}
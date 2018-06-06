package com.grey.virtualfais;

import com.grey.virtualfais.models.Level;

import java.util.LinkedList;
import java.util.List;

class Node
{
    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int x;
    public int y;
}

public class PathFinder
{
    private Node startNode;
    private final int FIRST_FLOOR_ID  = 2131165296;
    private final int SECOND_FLOOR_ID = 2131165310;
    private final int THIRD_FLOOR_ID  = 2131165311;

    public PathFinder()
    {
        startNode = new Node(8512, 4832);
    }

    private Character getSegmentFromRoomId(String roomId)
    {
        return roomId.charAt(0);
    }

    private Character getFloorFromRoomId(String roomId)
    {
        return roomId.charAt(2);
    }

    public List<Node> goToSelectedRoom(String roomId, Level level)
    {
        int floorId = level.getMaskId();
        String segmentWithFloor = "" + getSegmentFromRoomId(roomId) + getFloorFromRoomId(roomId);
        switch (segmentWithFloor)
        {
            case "B0":
            case "C0":
            case "D0":
                return goToLeftStairs();
            case "B1":
                return goToSegmentB1(floorId);
            case "B2":
                return goToSegmentB2(floorId);
            case "C1":
                return goToSegmentC1(floorId);
            case "C2":
                return goToSegmentC2(floorId);
            case "D1":
                return goToSegmentD1(floorId);
            case "D2":
                return goToSegmentD2(floorId);
            case "E0":
                return goToSegmentE0();
            case "E1":
                return goToSegmentE1(floorId);
            case "F0":
                return goToSegmentF0();
            case "F1":
                return goToSegmentF1(floorId);
            case "F2":
                return goToSegmentF2(floorId);
            case "G0":
                return goToSegmentG0();
            case "G1":
                return goToSegmentG1(floorId);
            case "G2":
                return goToSegmentG2(floorId);
            case "H0":
                return goToSegmentH0();
            case "H1":
                return goToSegmentH1(floorId);
            case "H2":
                return goToSegmentH2(floorId);
            case "J0":
                return goToSegmentJ0();
            default:
                return new LinkedList<>();
        }
    }

    private List<Node> goToRightStairs()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(8708, 4143));
        path.add(new Node(9145, 3998));
        path.add(new Node(9350, 4178));
        path.add(new Node(9362, 3828));
        return path;
    }

    private List<Node> goToMiddleStairs()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(7702, 3851));
        path.add(new Node(7895, 3677));
        return path;
    }

    private List<Node> goToLeftStairs()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(7818, 5028));
        path.add(new Node(7692, 5492));
        path.add(new Node(7856, 5675));
        path.add(new Node(7557, 5685));
        return path;
    }

    private List<Node> goToSegmentB1(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToLeftStairs());
        }
        else if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(7320, 5624));
            path.add(new Node(5983, 5634));
        }
        return path;
    }

    private List<Node> goToSegmentB2(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToLeftStairs());
        }
        else if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(7320, 5624));
            path.add(new Node(5983, 5634));
        }
        return path;
    }

    private List<Node> goToSegmentC1(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentB1(floorId));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(2984, 5628));
        }
        return path;
    }

    private List<Node> goToSegmentC2(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentB2(floorId));
        if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(2984, 5628));
        }
        return path;
    }

    private List<Node> goToSegmentD1(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentC1(floorId));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(1625, 5644));
        }
        return path;
    }

    private List<Node> goToSegmentD2(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentC2(floorId));
        if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(1625, 5644));
        }
        return path;
    }

    private List<Node> goToSegmentE0()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(7555, 3880));
        path.add(new Node(7237, 3551));
        return path;
    }

    private List<Node> goToSegmentE1(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToMiddleStairs());
        }
        else if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(7840, 3696));
            path.add(new Node(7624, 3796));
            path.add(new Node(7288, 3484));
        }
        return path;
    }

    private List<Node> goToSegmentF0()
    {
        List<Node> path = new LinkedList<>(goToSegmentE0());
        path.add(new Node(7011, 3680));
        path.add(new Node(6585, 3600));
        path.add(new Node(6375, 3185));
        path.add(new Node(6009, 2985));
        return path;
    }

    private List<Node> goToSegmentF1(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentE1(floorId));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(6828, 3592));
            path.add(new Node(6492, 3328));
            path.add(new Node(6392, 2996));
            path.add(new Node(6080, 2732));
        }
        return path;
    }

    private List<Node> goToSegmentF2(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentB2(floorId));
        if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(5680, 5624));
            path.add(new Node(5680, 4432));
            path.add(new Node(4192, 3576));
            path.add(new Node(3952, 3392));
            path.add(new Node(4616, 2216));
        }
        return path;
    }

    private List<Node> goToSegmentH0()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(8708, 4143));
        path.add(new Node(9145, 3998));
        path.add(new Node(9344, 3377));
        path.add(new Node(9344, 2765));
        return path;
    }

    private List<Node> goToSegmentH1(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToRightStairs());
        }
        else if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(9456, 3508));
            path.add(new Node(9456, 2656));
        }
        return path;
    }

    private List<Node> goToSegmentH2(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToRightStairs());
        }
        else if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(9456, 3508));
            path.add(new Node(9456, 2656));
        }
        return path;
    }

    private List<Node> goToSegmentG0()
    {
        List<Node> path = new LinkedList<>(goToSegmentH0());
        path.add(new Node(9308, 1032));
        path.add(new Node(9020, 820));
        return path;
    }

    private List<Node> goToSegmentG1(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentH1(floorId));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(9416, 872));
            path.add(new Node(9144, 632));
        }
        return path;
    }

    private List<Node> goToSegmentG2(int floorId)
    {
        List<Node> path = new LinkedList<>(goToSegmentH2(floorId));
        if(floorId == THIRD_FLOOR_ID)
        {
            path.add(new Node(9416, 872));
            path.add(new Node(9144, 632));
        }
        return path;
    }

    private List<Node> goToSegmentJ0()
    {
        List<Node> path = new LinkedList<>();
        path.add(new Node(7555, 3880));
        path.add(new Node(7492, 3816));
        path.add(new Node(6756, 3917));
        path.add(new Node(4112, 2423));
        path.add(new Node(4000, 1668));
        path.add(new Node(3824, 1448));
        return path;
    }

    private List<Node> goToSegmentJ1(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToSegmentJ0());
            path.add(new Node(3648, 1324));
            path.add(new Node(3824, 984));
        }
        return path;
    }

    public Node getStartNode()
    {
        return startNode;
    }
}

package com.grey.virtualfais;

import com.grey.virtualfais.models.Level;

import java.util.LinkedList;
import java.util.List;

import android.util.Log;

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
        String roomNumber = roomId.substring(4);
        switch (segmentWithFloor)
        {
            case "A0":
                return goToSegmentA0(floorId, roomNumber);
            case "A1":
                return goToSegmentA1(floorId, roomNumber);
            case "A2":
                return goToSegmentA2(floorId, roomNumber);
            case "B0":
            case "C0":
            case "D0":
                return goToLeftStairs();
            case "B1":
                return goToSegmentB1(floorId, roomNumber);
            case "B2":
                return goToSegmentB2(floorId);
            case "C1":
                return goToSegmentC1(floorId, roomNumber);
            case "C2":
                return goToSegmentC2(floorId);
            case "D1":
                return goToSegmentD1(floorId, roomNumber);
            case "D2":
                return goToSegmentD2(floorId);
            case "E0":
                return goToSegmentE0(floorId);
            case "E1":
                return goToSegmentE1(floorId);
            case "F0":
                return goToSegmentF0(floorId, roomNumber);
            case "F1":
                return goToSegmentF1(floorId, roomNumber);
            case "F2":
                return goToSegmentF2(floorId);
            case "G0":
                return goToSegmentG0(floorId, roomNumber);
            case "G1":
                return goToSegmentG1(floorId, roomNumber);
            case "G2":
                return goToSegmentG2(floorId);
            case "H0":
                return goToSegmentH0(floorId, roomNumber);
            case "H1":
                return goToSegmentH1(floorId, roomNumber);
            case "H2":
                return goToSegmentH2(floorId);
            case "J0":
                return goToSegmentJ0(floorId, roomNumber);
            case "J1":
                return goToSegmentJ1(floorId, roomNumber);
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

    private List<Node> goToJ0Stairs()
    {
        List<Node> path = new LinkedList<>(goToSegmentJ0(FIRST_FLOOR_ID, ""));
        path.add(new Node(3632, 1324));
        path.add(new Node(3828, 1008));
        return path;
    }

    private List<Node> goToSegmentA0(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>();
        if(floorId != FIRST_FLOOR_ID)
        {
            return path;
        }
        switch (roomNumber)
        {
            case "01":
                path.add(new Node(7800, 5044));
                path.add(new Node(7744, 5476));
                path.add(new Node(8108, 5796));
                break;
            case "03":
                path.add(new Node(7800, 5044));
                path.add(new Node(7744, 5476));
                path.add(new Node(8060, 5868));
                break;
            case "05":
                path.add(new Node(7800, 5044));
                path.add(new Node(7744, 5476));
                path.add(new Node(7868, 5652));
                path.add(new Node(7808, 5892));
                break;
            case "07":
                path.add(new Node(7800, 5044));
                path.add(new Node(7744, 5476));
                path.add(new Node(7868, 5652));
                path.add(new Node(7732, 5828));
                break;
            case "11":
                path.add(new Node(7800, 5044));
                path.add(new Node(7744, 5476));
                path.add(new Node(7220, 5592));
                path.add(new Node(7152, 5740));
                break;
            case "12":
                path.add(new Node(8300, 4204));
                break;
            case "13":
                path.add(new Node(8736, 4124));
                path.add(new Node(9160, 4032));
                path.add(new Node(9252, 3520));
                path.add(new Node(9440, 3436));
                break;
            case "15":
                path.add(new Node(8736, 4124));
                path.add(new Node(9160, 4032));
                path.add(new Node(9332, 4228));
                path.add(new Node(9508, 4080));
                break;
            case "17":
                path.add(new Node(8736, 4124));
                path.add(new Node(9160, 4032));
                path.add(new Node(9332, 4228));
                path.add(new Node(9552, 4196));
                break;
            case "19":
                path.add(new Node(8736, 4124));
                path.add(new Node(9160, 4032));
                path.add(new Node(9332, 4228));
                path.add(new Node(9556, 4380));
                break;
            case "21":
                path.add(new Node(8736, 4124));
                path.add(new Node(9160, 4032));
                path.add(new Node(9332, 4228));
                path.add(new Node(9488, 4440));
                break;

        }
        return path;
    }

    private List<Node> goToSegmentA1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToMiddleStairs());
        }
        else if(floorId == SECOND_FLOOR_ID)
        {
            switch (roomNumber)
            {
                case "06":
                    path.add(new Node(7808, 3700));
                    path.add(new Node(7712, 4188));
                    path.add(new Node(7436, 4668));
                    path.add(new Node(7572, 4812));
                    break;
                case "08":
                    path.add(new Node(7800, 3784));
                    path.add(new Node(7984, 3916));
                    path.add(new Node(8528, 3612));
                    path.add(new Node(8616, 3708));
                    break;
            }
        }
        return path;
    }

    private List<Node> goToSegmentA2(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            if(roomNumber.equals("07")) {
                path = new LinkedList<>(goToRightStairs());
            }
            else if(roomNumber.equals("02"))
            {
                path = new LinkedList<>(goToLeftStairs());
            }
        }
        else if(floorId == THIRD_FLOOR_ID)
        {
            switch (roomNumber)
            {
                case "02":
                    path.add(new Node(7336, 5634));
                    path.add(new Node(7348, 5474));
                    path.add(new Node(8140, 5562));
                    path.add(new Node(8308, 5440));
                    break;
                case "07":
                    path.add(new Node(9456, 3496));
                    path.add(new Node(9306, 3522));
                    path.add(new Node(9368, 4298));
                    path.add(new Node(9578, 4454));
                    break;
            }
        }
        return path;
    }

    private List<Node> goToSegmentB1(int floorId, String roomNumber)
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
            switch (roomNumber)
            {
                case "02":
                    path.add(new Node(5672, 5624));
                    path.add(new Node(3904, 5628));
                    path.add(new Node(3738, 5718));
                    break;
                case "22":
                    path.add(new Node(5672, 5624));
                    path.add(new Node(5452, 5720));
                    break;
                case "24":
                    path.add(new Node(5672, 5624));
                    path.add(new Node(5668, 5716));
                    break;
                case "46":
                    path.add(new Node(5672, 5624));
                    path.add(new Node(5676, 4416));
                    path.add(new Node(5660, 4296));
                    break;
                case "50":
                    path.add(new Node(5672, 5624));
                    path.add(new Node(5676, 4416));
                    path.add(new Node(5248, 4160));
                    path.add(new Node(5276, 4064));
                    break;
            }
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

    private List<Node> goToSegmentC1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>(goToSegmentB1(floorId, ""));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(2984, 5628));
            switch (roomNumber)
            {
                case "09":
                    path.add(new Node(2676, 5592));
                    path.add(new Node(2976, 5088));
                    path.add(new Node(2888, 5008));
                    break;
                case "14":
                    path.add(new Node(2676, 5592));
                    path.add(new Node(2976, 5088));
                    path.add(new Node(3100, 5044));
                    break;
                case "28":
                    path.remove(path.size()-1);
                    path.add(new Node(5672, 5632));
                    path.add(new Node(5668, 4420));
                    path.add(new Node(4192, 3552));
                    path.add(new Node(4192, 3420));
                    break;
            }
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

    private List<Node> goToSegmentD1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>(goToSegmentC1(floorId, ""));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(1625, 5644));
            switch (roomNumber)
            {
                case "17":
                    path.add(new Node(680, 5596));
                    path.add(new Node(776, 5384));
                    path.add(new Node(700, 5312));
                    break;
                case "63":
                    path.remove(path.size()-1);
                    path.remove(path.size()-1);
                    path.add(new Node(5672, 5632));
                    path.add(new Node(5668, 4420));
                    path.add(new Node(4192, 3552));
                    path.add(new Node(3210, 2978));
                    path.add(new Node(3204, 2880));
                    break;
            }
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

    private List<Node> goToSegmentE0(int floorId)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID) {

            path.add(new Node(7555, 3880));
            path.add(new Node(7237, 3551));
        }
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

    private List<Node> goToSegmentF0(int floorId, String roomNumber)
    {
        if(floorId != FIRST_FLOOR_ID)
        {
            return new LinkedList<>();
        }
        List<Node> path = new LinkedList<>(goToSegmentE0(floorId));
        path.add(new Node(7011, 3680));
        path.add(new Node(6585, 3600));
        path.add(new Node(6375, 3185));
        path.add(new Node(6009, 2985));
        switch (roomNumber)
        {
            case "01":
                path.add(new Node(5568, 2836));
                path.add(new Node(4572, 2260));
                break;
            case "03":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(4844, 1816));
                break;
            case "04":
                path.add(new Node(5568, 2836));
                path.add(new Node(5080, 2476));
                break;
            case "05":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(4940, 1632));
                break;
            case "07":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5016, 1520));
                break;
            case "09":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5132, 1284));
                break;
            case "11":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5312, 992));
                break;
            case "13":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5380, 884));
                break;
            case "15":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5460, 744));
                break;
            case "17":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5540, 608));
                break;
            case "18":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(4968, 1936));
                break;
            case "19":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5644, 424));
                break;
            case "20":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5232, 1444));
                break;
            case "21":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5776, 392));
                break;
            case "22":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5300, 1328));
                break;
            case "23":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5792, 464));
                break;
            case "25":
                path.add(new Node(5568, 2836));
                path.add(new Node(4656, 2292));
                path.add(new Node(5120, 1484));
                path.add(new Node(5488, 848));
                path.add(new Node(5848, 704));
                break;
        }
        return path;
    }

    private List<Node> goToSegmentF1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>(goToSegmentE1(floorId));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(6828, 3592));
            path.add(new Node(6492, 3328));
            path.add(new Node(6392, 2996));
            path.add(new Node(6080, 2732));
            switch (roomNumber)
            {
                case "02":
                    path.add(new Node(6052, 2560));
                    break;
                case "03":
                    path.add(new Node(5476, 2340));
                    path.add(new Node(5236, 2320));
                    break;
                case "06":
                    path.add(new Node(5476, 2340));
                    path.add(new Node(5520, 2256));
                    break;
                case "08":
                    path.add(new Node(5476, 2340));
                    path.add(new Node(5272, 2112));
                    break;
                case "13":
                    path.add(new Node(5476, 2340));
                    path.add(new Node(4788, 1936));
                    path.add(new Node(5152, 1296));
                    path.add(new Node(5120, 1180));
                    break;
                case "17":
                    path.add(new Node(5476, 2340));
                    path.add(new Node(4788, 1936));
                    path.add(new Node(5152, 1296));
                    path.add(new Node(5600, 400));
                    break;
            }
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

    private List<Node> goToSegmentH0(int floorId, String roomNumber)
    {
        if(floorId != FIRST_FLOOR_ID)
        {
            return new LinkedList<>();
        }
        List<Node> path = new LinkedList<>();
        path.add(new Node(8708, 4143));
        path.add(new Node(9145, 3998));
        path.add(new Node(9344, 3377));
        path.add(new Node(9344, 2765));
        switch(roomNumber)
        {
            case "01":
                path.add(new Node(9280, 2696));
                break;
            case "02":
                path.add(new Node(9428, 2676));
                break;
            case "03":
                path.add(new Node(9280, 2540));
                break;
            case "04":
                path.add(new Node(9432, 2392));
                break;
            case "05":
                path.add(new Node(9276, 2380));
                break;
            case "06":
                path.add(new Node(9360, 2152));
                path.add(new Node(9432, 2088));
                break;
            case "07":
                path.add(new Node(9276, 2212));
                break;
            case "08":
                path.add(new Node(9360, 2152));
                path.add(new Node(9432, 1916));
                break;
            case "09":
                path.add(new Node(9360, 2152));
                path.add(new Node(9268, 2076));
                break;
            case "10":
                path.add(new Node(9360, 2152));
                path.add(new Node(9432, 1728));
                break;
            case "11":
                path.add(new Node(9360, 2152));
                path.add(new Node(9268, 1708));
                break;
            case "12":
                path.add(new Node(9360, 2152));
                path.add(new Node(9436, 1484));
                break;
            case "13":
                path.add(new Node(9360, 2152));
                path.add(new Node(9340, 1412));
                path.add(new Node(9268, 1268));
                break;
        }
        return path;
    }

    private List<Node> goToSegmentH1(int floorId, String roomNumber)
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
            switch (roomNumber)
            {
                case "01":
                    path.add(new Node(9372, 2556));
                    break;
                case "02":
                    path.add(new Node(9532, 2576));
                    break;
                case "03":
                    path.add(new Node(9368, 2316));
                    break;
                case "04":
                    path.add(new Node(9536, 2364));
                    break;
                case "05":
                    path.add(new Node(9368, 2028));
                    break;
                case "06":
                    path.add(new Node(9536, 2156));
                    break;
                case "07":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9372, 1784));
                    break;
                case "08":
                    path.add(new Node(9536, 1956));
                    break;
                case "09":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9372, 1640));
                    break;
                case "10":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9536, 1756));
                    break;
                case "11":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9380, 1428));
                    break;
                case "12":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9532, 1540));
                    break;
                case "13":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9448, 1268));
                    path.add(new Node(9372, 1200));
                    break;
                case "14":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9536, 1332));
                    break;
                case "15":
                    path.add(new Node(9452, 1844));
                    path.add(new Node(9448, 1268));
                    path.add(new Node(9372, 1084));
                    break;
            }
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

    private List<Node> goToSegmentG0(int floorId, String roomNumber)
    {
        if(floorId != FIRST_FLOOR_ID)
        {
            return new LinkedList<>();
        }
        List<Node> path = new LinkedList<>(goToSegmentH0(floorId,""));
        path.add(new Node(9308, 1032));
        path.add(new Node(9020, 820));
        switch(roomNumber)
        {
            case "01":
                path.add(new Node(8828, 896));
                break;
            case "02":
                path.add(new Node(8392, 708));
                break;
            case "03":
                path.add(new Node(8592, 840));
                path.add(new Node(8588, 960));
                break;
            case "04":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7692, 740));
                break;
            case "05":
                path.add(new Node(8592, 840));
                path.add(new Node(8440, 960));
                break;
            case "06":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7552, 740));
                break;
            case "07":
                path.add(new Node(8592, 840));
                path.add(new Node(8288, 960));
                break;
            case "08":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7404, 740));
                break;
            case "09":
                path.add(new Node(8592, 840));
                path.add(new Node(8148, 960));
                break;
            case "10":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7252, 740));
                break;
            case "11":
                path.add(new Node(8592, 840));
                path.add(new Node(7992, 960));
                break;
            case "12":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(7108, 740));
                break;
            case "13":
                path.add(new Node(8592, 840));
                path.add(new Node(7844, 960));
                break;
            case "14":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6956, 740));
                break;
            case "15":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7684, 960));
                break;
            case "16":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6820, 740));
                break;
            case "17":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7544, 960));
                break;
            case "18":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6660, 740));
                break;
            case "19":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7316, 900));
                break;
            case "20":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6504, 740));
                break;
            case "21":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(7020, 900));
                break;
            case "23":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6800, 900));
                break;
            case "25":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6612, 900));
                break;
            case "27":
                path.add(new Node(8592, 840));
                path.add(new Node(7784, 848));
                path.add(new Node(7180, 812));
                path.add(new Node(6408, 900));
                break;
        }
        return path;
    }

    private List<Node> goToSegmentG1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>(goToSegmentH1(floorId, ""));
        if(floorId == SECOND_FLOOR_ID)
        {
            path.add(new Node(9416, 872));
            path.add(new Node(9144, 632));
            switch (roomNumber)
            {
                case "07":
                    path.add(new Node(7664, 676));
                    path.add(new Node(7376, 752));
                    break;
                case "08":
                    path.add(new Node(7664, 676));
                    path.add(new Node(7520, 580));
                    break;
            }
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

    private List<Node> goToSegmentJ1(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>();
        if(floorId == FIRST_FLOOR_ID)
        {
            path = new LinkedList<>(goToJ0Stairs());
        }
        else
        {
            switch (roomNumber)
            {
                case "01":
                    path.add(new Node(3836, 900));
                    path.add(new Node(4080, 900));
                    break;
                case "02":
                    path.add(new Node(3836, 900));
                    path.add(new Node(3628, 1060));
                    break;
                case "03":
                    path.add(new Node(3836, 900));
                    path.add(new Node(3940, 1080));
                    break;
                case "05":
                    path.add(new Node(3836, 900));
                    path.add(new Node(3656, 1300));
                    break;
                case "04":
                    path.add(new Node(3836, 900));
                    path.add(new Node(3536, 1372));
                    path.add(new Node(3408, 1424));
                    break;
                case "07":
                    path.add(new Node(3836, 900));
                    path.add(new Node(3536, 1372));
                    path.add(new Node(3532, 1528));
                    break;
            }
        }
        return path;
    }

    private List<Node> goToSegmentJ0(int floorId, String roomNumber)
    {
        List<Node> path = new LinkedList<>();
        if(floorId != FIRST_FLOOR_ID)
        {
            return path;
        }
        path.add(new Node(7555, 3880));
        path.add(new Node(7492, 3816));
        path.add(new Node(6756, 3917));
        path.add(new Node(4112, 2423));
        path.add(new Node(4000, 1668));
        path.add(new Node(3824, 1448));
        switch (roomNumber)
        {
            case "01":
                path.add(new Node(3816, 1344));
                break;
            case "03":
                path.add(new Node(3584, 1204));
                break;
            case "04":
                path.add(new Node(3388, 1312));
                break;
            case "05":
                path.add(new Node(3648, 1328));
                path.add(new Node(3852, 1076));
                break;
            case "06":
                path.add(new Node(3168, 1064));
                path.add(new Node(2694, 878));
                break;
            case "07":
                path.add(new Node(3464, 1136));
                break;
            case "09":
                path.add(new Node(3168, 1064));
                path.add(new Node(3228, 996));
                break;
            case "15":
                path.add(new Node(3168, 1064));
                path.add(new Node(2948, 834));
                break;
            case "17":
                path.add(new Node(3168, 1064));
                path.add(new Node(2802, 746));
                break;
        }
        return path;
    }

    public Node getStartNode()
    {
        return startNode;
    }
}

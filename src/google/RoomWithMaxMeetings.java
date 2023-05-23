package google;

import sun.reflect.generics.tree.Tree;

import java.util.*;

public class RoomWithMaxMeetings {

    class Appointment{
        int startTime;
        int endTime;
        int roomNo;

        Appointment(int startTime, int endTime, int roomNo){
            this.startTime=startTime;
            this.endTime=endTime;
            this.roomNo = roomNo;
        }
    }

    public static void main(String[] args) {
        RoomWithMaxMeetings r = new RoomWithMaxMeetings();
        r.findRoomWithMaxMeetings(new int[][]{{5,10}, {8,18}, {11,113}, {25,5}, {32, 3}, {200,5}}, 2); // 3 3
        r.findRoomWithMaxMeetings(new int[][]{{1,1}, {2,1}, {2,1}}, 5); // 2 1
        r.findRoomWithMaxMeetings(new int[][]{{1,10}, {8,25}, {9, 10}}, 2); // 2 1
        r.findRoomWithMaxMeetings(new int[][]{{1,5}, {5,5}, {8, 7},{8, 192}}, 2); // 2 2
        r.findRoomWithMaxMeetings(new int[][]{{1,14}, {4,25}, {10, 100}, {200,10}}, 2); // 3 1
        r.findRoomWithMaxMeetings(new int[][]{{1,1000}, {2,1000}, {3, 20}, {4,20}, {5,20}, {24, 10}}, 5); // 1 1 2 1 1
        r.findRoomWithMaxMeetings(new int[][]{{1,1000}, {2,1000}, {3, 3}, {6,3}, {9,3}, {12, 3}}, 5); // 1 1 4 0 0
        r.findRoomWithMaxMeetings(new int[][]{{1,1000}, {2,1000}, {3, 3}, {6,3}, {9,3}, {11, 3}}, 5); // 1 1 3 1 0
        r.findRoomWithMaxMeetings(new int[][]{{1,1000}, {2,1000}, {3, 3}, {6,3}, {9,3}, {11, 3}, {2000, 10}}, 5); // 2 1 3 1 0
        System.out.println(" -------------------- ");

    }

    private int findRoomWithMaxMeetings(int[][] customers, int N) {

        Arrays.sort(customers, (a,b) ->{
            return a[0]-b[0];
        });

        PriorityQueue<Appointment> q = new PriorityQueue<>((a,b)->{
            if(a.endTime==b.endTime){
                return a.roomNo-b.roomNo;
            }else{
                return a.endTime-b.endTime;
            }
        });

        int[] rooms = new int[N];
        int roomNo = 0;

        for(int i=0;i<customers.length;i++){
            int startTime = customers[i][0];
            int endTime = startTime + customers[i][1];

            if(roomNo<N){
                if(!q.isEmpty() && q.peek().endTime<=startTime){
                    //New room available but we can take the used room
                    List<Appointment> expiredList = new ArrayList<>();
                    Appointment prev = null;
                    while(!q.isEmpty() && q.peek().endTime<=startTime){
                        if(prev!=null){
                            Appointment curr = q.poll();
                            if(curr.roomNo<prev.roomNo){
                                expiredList.add(prev);
                                prev=curr;
                            }
                        }else {
                            prev=q.poll();
                        }
                    }
                    Appointment lastEndingApp = prev;
                    lastEndingApp.startTime=startTime;
                    lastEndingApp.endTime=endTime;
                    q.add(lastEndingApp);
                    rooms[lastEndingApp.roomNo]++;
                }else{
                    //Cannot take the already occupied room so assigning to a brand new room
                    q.add(new Appointment(startTime, endTime, roomNo));
                    rooms[roomNo]++;
                    roomNo++;
                }
            }else{
                if(q.peek().endTime<=startTime){
                    //New room not available but previous rooms are free
                    List<Appointment> expiredList = new ArrayList<>();
                    Appointment prev = null;
                    while(!q.isEmpty() && q.peek().endTime<=startTime){
                        if(prev!=null){
                            Appointment curr = q.poll();
                            if(curr.roomNo<prev.roomNo){
                                expiredList.add(prev);
                                prev=curr;
                            }
                        }else {
                            prev=q.poll();
                        }
                    }
                    Appointment lastEndingApp = prev;
                    lastEndingApp.startTime=startTime;
                    lastEndingApp.endTime=endTime;
                    q.add(lastEndingApp);
                    rooms[lastEndingApp.roomNo]++;
                    q.addAll(expiredList);
                }else{
                    //New room not available and previous rooms also not available
                    Appointment lastEndingApp = q.poll();
                    lastEndingApp.startTime=lastEndingApp.endTime;
                    lastEndingApp.endTime=lastEndingApp.startTime + customers[i][1];
                    q.add(lastEndingApp);
                    rooms[lastEndingApp.roomNo]++;

                }
            }
        }

        int max=-1, maxRoom=-1;
        for(int i=0;i<rooms.length;i++){
            if(max< rooms[i]){
                max=rooms[i];
                maxRoom=i;
            }
            System.out.println(String.format("Room no : %d Meeting count : %d", i, rooms[i]));
        }
        System.out.println("Max meeting occured in : " + maxRoom);
        System.out.println("----");

        return maxRoom;

    }

}

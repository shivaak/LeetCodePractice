package general;

import java.util.Arrays;

public class RankTeamsByVotes {

    public static void main(String[] args) {
        RankTeamsByVotes r = new RankTeamsByVotes();
        System.out.println(r.rankTeams(new String[]{"ABC","ACB","ABC","ACB","ACB"})); // ACB
        System.out.println(r.rankTeams(new String[]{"WXYZ","XYZW"})); // XWYZ
        System.out.println(r.rankTeams(new String[]{"ZMNAGUEDSJYLBOPHRQICWFXTVK"})); // ZMNAGUEDSJYLBOPHRQICWFXTVK
        System.out.println(r.rankTeams(new String[]{"XYZ","ZYX","ZYX"})); // ZXY
    }

    private String rankTeams(String[] votes) {

        int totalRanks = votes[0].length();
        int[][] voteMap = new int[26][totalRanks];

        for(int i=0;i<votes.length;i++){
            for(int j=0;j<votes[i].length();j++){
                char c = votes[i].charAt(j);
                voteMap[c-'A'][j]++;
            }
        }

        Character[] res = new Character[totalRanks];
        for(int i=0;i<totalRanks;i++){
            res[i]=votes[0].charAt(i);
        }

        Arrays.sort(res, (a,b)->{
            for(int i=0;i<totalRanks;i++){
                if(voteMap[a-'A'][i]!=voteMap[b-'A'][i]){
                    return voteMap[b-'A'][i] - voteMap[a-'A'][i] ;
                }
            }
            return a-b;
        });

        StringBuilder resString = new StringBuilder();
        for (Character ch : res) {
            resString.append(ch);
        }

        return resString.toString();
    }
}

package Atlassian;

/*
Problem Statement :

interface MostPopular {
    void increasePopularity(Integer contentId);
    Integer mostPopular();
    void decreasePopularity(Integer contentId);
}
Sample execution:
[
  popularityTracker.increasePopularity(7); : 7 -> 1
  popularityTracker.increasePopularity(7); : 7 -> 2
  popularityTracker.increasePopularity(8); : 7 -> 2 , 8 -> 1
  popularityTracker.mostPopular();        // returns 7
  popularityTracker.increasePopularity(8); : 7 -> 2, 8 -> 2
  popularityTracker.increasePopularity(8); : 7 -> 2, 8 -> 3
  popularityTracker.mostPopular();        // returns 8
  popularityTracker.decreasePopularity(8); : 7 -> 2, 8 -> 2
  popularityTracker.decreasePopularity(8); : 7 -> 2, 8 -> 1
  popularityTracker.mostPopular();        // returns 7
  popularityTracker.decreasePopularity(7); : 7 -> 1, 8 -> 1
  popularityTracker.decreasePopularity(7); : 7 -> 0, 8 -> 1
  popularityTracker.decreasePopularity(8); : 7 -> 0, 8 -> 0
  popularityTracker.mostPopular();        // returns -1 since there is no content with popularity greater than 0
  popularityTracker.increasePopularity(7); : 7 -> 1, 8 -> 0
  popularityTracker.increasePopularity(8); : 7 -> 1, 8 -> 1
  popularityTracker.mostPopular();     // Return any

  O(1) - > Most popular
  O(log n) -> for each content insertion
   n * O(log n) -> increase & decrease

   HashMap<Integer, Integer> Contentid, Popularity
   PriorityQueue<Integer>
   TreeMap<Integer, List<Integer>> Popularity, Contentid

]
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

interface MostPopular {
    void increasePopularity(Integer contentId);
    Integer mostPopular();
    void decreasePopularity(Integer contentId);
}

class MostPopularContent implements  MostPopular{

    HashMap<Integer, Integer> contentMap;
    TreeMap<Integer, List<Integer>> popularityMap;

    public MostPopularContent(){
        contentMap = new HashMap<>();
        popularityMap = new TreeMap<>((a,b)-> b-a);
    }

    @Override
    public void increasePopularity(Integer contentId) {
        if(!contentMap.containsKey(contentId)){ // O(1)
            contentMap.put(contentId, 0);
        }
       int popularityCount = contentMap.get(contentId); // O(1)
       int newPopularityCount = popularityCount + 1;
       contentMap.put(contentId, newPopularityCount);

       if(popularityCount>0){
           popularityMap.get(popularityCount).remove(contentId); // log n
       }

       if(!popularityMap.containsKey(newPopularityCount)){
           popularityMap.put(newPopularityCount, new ArrayList<>());
       }
       popularityMap.get(newPopularityCount).add(contentId);
    }

    @Override
    public Integer mostPopular() {
        if(popularityMap.isEmpty()){
            return -1;
        }
        int size = popularityMap.firstEntry().getValue().size();
        return popularityMap.size()>0 ? popularityMap.firstEntry().getValue().get(size-1) : -1;
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        if(!contentMap.containsKey(contentId)){
            throw new IllegalArgumentException("Invalid content. Cannot be decreased");
        }
        int popularityCount = contentMap.get(contentId);
        int newPopularityCount = popularityCount - 1;
        if(newPopularityCount==0){
            contentMap.remove(contentId);
        }else{
            contentMap.put(contentId, newPopularityCount);
        }

        popularityMap.get(popularityCount).remove(contentId);
        if(popularityMap.get(popularityCount).size()==0){
            popularityMap.remove(popularityCount);
        }

        if(!popularityMap.containsKey(newPopularityCount)){
            popularityMap.put(newPopularityCount, new ArrayList<>());
        }
        popularityMap.get(newPopularityCount).add(contentId);
        popularityMap.remove(0);
    }
}

public class Main {
    public static void main(String[] args) {
        MostPopularContent popularityTracker = new MostPopularContent();

        popularityTracker.increasePopularity(7); // 7 -> 1
        System.out.println(popularityTracker.mostPopular()); // returns 7
        popularityTracker.increasePopularity(7);// 7 -> 2
        popularityTracker.increasePopularity(8); // 7 -> 2 , 8 -> 1
        System.out.println(popularityTracker.mostPopular());        // returns 7
        popularityTracker.increasePopularity(8); // 7 -> 2, 8 -> 2
        popularityTracker.increasePopularity(8); // 7 -> 2, 8 -> 3
        System.out.println(popularityTracker.mostPopular());        // returns 8
        popularityTracker.decreasePopularity(8); // 7 -> 2, 8 -> 2
        popularityTracker.decreasePopularity(8); // 7 -> 2, 8 -> 1
        System.out.println(popularityTracker.mostPopular());        // returns 7
        popularityTracker.decreasePopularity(7); // 7 -> 1, 8 -> 1
        popularityTracker.decreasePopularity(7); // 7 -> 0, 8 -> 1
        popularityTracker.decreasePopularity(8); // 7 -> 0, 8 -> 0
        System.out.println(popularityTracker.mostPopular());        // returns -1 since there is no content with popularity greater than 0
        popularityTracker.increasePopularity(7); // 7 -> 1, 8 -> 0
        popularityTracker.increasePopularity(8); // 7 -> 1, 8 -> 1
        System.out.println(popularityTracker.mostPopular());     // Return any : 8
    }
}
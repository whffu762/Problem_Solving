import java.util.Map;
import java.util.HashMap;

class Solution {
    
    Map<String, Integer> rank = new HashMap<>();
    String [] name;
    
    public String[] solution(String[] players, String[] callings) {
        
        for(int i=0;i<players.length;i++){
            rank.put(players[i], i);
        }
        
        name = players;
        
        for(int i=0;i<callings.length;i++){
            changeRank(callings[i]);
        }
        
        return name;
    }
    
    void changeRank(String player){
        
        int beforeRank = rank.get(player);
        
        int afterRank = beforeRank - 1;
        String otherPlayer = name[afterRank];
        
        rank.put(player, afterRank);
        rank.put(otherPlayer, beforeRank);
        
        name[beforeRank] = otherPlayer;
        name[afterRank] = player;
    }
}
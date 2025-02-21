class User {
    
    int time;
    int heal;
    int bonus;
    int maxHealth;
    int count = 0;
    
    User (int [] bandage, int health){
        
        time = bandage[0];
        heal = bandage[1];
        bonus = bandage[2];
        maxHealth = health;
    }
    
    
    int healPerSec(int nowHealth){
        
        if(++count == time){
            nowHealth += bonus;
            count = 0;
        }
        
        nowHealth += heal;
        
        if(nowHealth > maxHealth){
            return maxHealth;
        }
        
        return nowHealth;
    }
    
    int damaged(int nowHealth, int damage){
        
        count = 0;
        nowHealth -= damage;
        
        if(nowHealth < 1) {
            return -1;
        }
        
        return nowHealth;
    }
}

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        User user = new User(bandage, health);
        
        int i = 0;
        for(int sec=0;sec<attacks[attacks.length-1][0]+1;sec++){
            
            int time = attacks[i][0];
            int damage = attacks[i][1];
            
            if(time == sec){
                health = user.damaged(health, damage);
                i++;
                if(health == -1){
                    break;
                }
            } else {
                health = user.healPerSec(health);
            }
        }
        
        return health;
    }
}
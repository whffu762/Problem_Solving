class Solution {
    
    int [] position = new int [2];
    String [][] map;
    
    public int[] solution(String[] park, String[] routes) {
        
        map = new String [park.length][park[0].length()];
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[0].length();j++){

                map[i][j] = String.valueOf(park[i].charAt(j));
                if(map[i][j].equals("S")){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        
        System.out.println("start : " + position[0] + " " + position[1]);
        
        for(int i=0;i<routes.length;i++){
            
            String [] command = routes[i].split(" ");
            switch(command[0]) {
                case "E" : moveEast(Integer.parseInt(command[1]));
                    break;
                case "W" : moveWest(Integer.parseInt(command[1]));
                    break;
                case "S" : moveSouth(Integer.parseInt(command[1]));
                    break;
                case "N" : moveNorth(Integer.parseInt(command[1]));
                    break;
            } 
            
            System.out.println(position[0] + " " + position[1]);
        }
    
        return this.position;
    }
    
    boolean checkValid(int row, int col){
        
        if(row > -1 && row < map.length && col > -1 && col < map[0].length){
            return !map[row][col].equals("X");
        }
        
        return false;
    }
    
    void moveEast(int move){
        
        int row = position[0];
        int col = position[1];
        
        for(int i=1;i<move+1;i++){
            
            if(!checkValid(row, col+i)){
                position[1] = col;
                return;
            }
            
            position[1] = col+i;
        }
    }
    
    void moveWest(int move){
        
        int row = position[0];
        int col = position[1];
        
        for(int i=1;i<move+1;i++){
            
            if(!checkValid(row, col-i)){
                position[1] = col;
                return;
            }
            
            position[1] = col-i;
        }
    }
    
    void moveSouth(int move){
        
        int row = position[0];
        int col = position[1];
        
        for(int i=1;i<move+1;i++){
            
            if(!checkValid(row+i, col)){
                position[0] = row;
                return;
            }
            
            position[0] = row+i;
        }
    }
    
    void moveNorth(int move){
        
        int row = position[0];
        int col = position[1];
        
        for(int i=1;i<move+1;i++){
            
            if(!checkValid(row-i, col)){
                position[0] = row;
                return;
            }
            
            position[0] = row-i;
        }
    }
}
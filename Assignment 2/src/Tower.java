public class Tower implements Figure {
//gameboard: 0 is square, 1 is figure

    Colors color;
    public Tower(Colors color){
        this.color = color;
    }
    public boolean isValidMove(Board gameboard,int x_current, int y_current, int x_move, int y_move, Player player){
        if(x_move > 8 || y_move > 8){
            return false;
        }
        //check if its a valid move for the tower
        else if(x_current == x_move || y_current == y_move){
            //check if position is occupied
            if(gameboard.board[x_move][y_move][1] != null){
                //check if there is a white or black figure
                if(player.getColor().equals(gameboard.board[x_move][y_move][1])){
                    return false; //figure at new position is from the same player
                } else {
                    return true;
                }
            }
        }
    public void move(){}
}

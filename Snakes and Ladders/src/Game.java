import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {

    private Player winner;
    public int current;
    public int board_width;
    public int board_height;
    public int boardsize;
    public List<Player> players;
    public List<Square> squares;


    //Constructor
    Game(){

        this.players = new ArrayList<Player>(4);
        this.winner = null;
        this.current = 0;
        this.squares = new ArrayList<Square>();
        createboard();
        addplayer(); // anzahl spieler vorher herausfinden wie auf Blatt beschrieben?
        Dice dice = new Dice();
        while (this.winner == null){
            int randomnumber = dice.dice();
            Player Currentplayer= players.get(this.current);
            int movenumber = checknumber(randomnumber);
            Currentplayer.move(movenumber);
            checklast(Currentplayer);
            // muss noch display einbauen für jeden move
            next();
        }




    }
    // create Board with squares
    public void createboard(){
        System.out.println("Please input height: ");
        Scanner s = new Scanner(System.in);
        String h_size = s.nextLine();
        this.board_height = Integer.parseInt(h_size);  // for boardsize
        System.out.println("Please input width: ");
        String w_size = s.nextLine();
        this.board_width = Integer.parseInt(w_size);
        boardsize = this.board_height * this.board_width;
        Square square = new FirstSquare(1, this);
        squares.add(square);
        for (int i = 2; i < boardsize; i++) {
            square = new Square(i, this);
            squares.add(square); // creates Squares in list
        }
        square = new LastSquare(boardsize,this);
        squares.add(square);
    }

    //add players to the board + muss noch jedem player den ersten Square zuteilen
    public void addplayer() {
        for (int i = 1; i <= 4; i++) {
            Player user = new Player(squares.get(0));
            user.setName(i);
            while (user.getName().equals("") && players.isEmpty()){
                System.out.println("Please input a Name");
                user.setName(i);

            }
            if(user.getName().equals("") && players.size() < 2){
                System.out.println("One is the loneliest number, but sure play by yourself");
                break;
            }
            else if(user.getName().equals("") && players.size() >= 2){
                System.out.println("No other was player entered");
                break;
            }
            players.add(user);
            user.square.enter(user);
        }
//        for(PlayerS player : players){
//            System.out.println(player.getName());
//        }
        System.out.println("\nGame starting now!");

    }

    public Square get_square(int move, Square requester){
        return squares.get(requester.position + move - 1);
    }

    //keeps track of whose turn it is
    public int next(){
        this.current += 1 % 4;
        return this.current;
    }
    public void checklast(Player current_player){
        if (squares.get(squares.size()-1).player_list != null){
            this.winner = current_player;
            System.out.format("Seems like %s just won the game.", current_player.name);
        }
    }
    public int checknumber(int to_move){
        int new_position = to_move + players.get(current).square.position;
        int adjusted_position = boardsize - (new_position - boardsize);
        if (new_position > boardsize){
            return adjusted_position;
        }
        else{
            return to_move;
        }
    }
//
//    return position
//    public int whatsquare(){
//        return players.get(turn()).pos;
//    }
//
//    public boolean islastsquare(Square s){
//        return true;
//    }

}
import java.util.*;

public class Main {
    public static ArrayList<pair> childArray = new ArrayList<pair>();
    public static String b[][] = new String[100][100];
    public static int m = 6;
    static int green = 0;
    static int red = 0;
    static int yellow = 0;

    public static void main(String[] args)
    {
        b[1][1] = b[0][1] = b[1][0] = b[2][0] = b[2][1] = b[3][0] = b[3][1] = b[3][2] = b[3][5] = b[4][2] = b[4][5] = b[5][2] = b[5][5] = "R";
        b[0][0] = b[0][4] = b[0][5] = b[0][3] = b[1][3] = b[1][2] = b[1][4] = b[1][5] = b[2][4] = b[3][3] = b[3][4] = b[4][3] = "Y";
        b[0][2] = b[1][3] = b[1][5] = b[2][2] = b[2][3] = b[2][5] = b[4][0] = b[4][4] = b[4][1] = b[5][0] = b[5][1] = b[5][3] = b[5][4] = "G";

        ShowTable();

        pair startState = new pair(0,0, b[0][0]);

        childArray.add(startState);

        totalNext(startState);
        ShowTable();

        totalNext(startState);
        ShowTable();

        for(int i = 0; i<childArray.size();i++){
            System.out.println(childArray.get(i).first+" "+childArray.get(i).second+" "+childArray.get(i).color);
        }

    }

    public static void totalNext(pair val){

        NextState s = new NextState(red,green,yellow);
        String next_color;
        next_color = s.generateNextColor(val,b);
        System.out.println(next_color);

        getChild(val);
        for(int i = 0; i<childArray.size(); i++)
        {
            childArray.get(i).color = next_color;
            b[childArray.get(i).first][childArray.get(i).second] = next_color;
        }

    }

    public static void ShowTable(){
        for(int i = 0; i<m; i++)
        {
            for(int j = 0; j<m; j++)
            {
                System.out.print(b[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void getChild(pair val){
        pair childs0 = new pair(val.first, val.second+1, b[val.first][val.second+1]);
        pair childs1 = new pair(val.first+1, val.second, b[val.first+1][val.second]);
        pair childs2 = new pair(val.first, val.second, b[val.first][val.second]);
        pair childs3 = new pair(val.first, val.second, b[val.first][val.second]);
        if(val.first!=0){
            childs2 = new pair(val.first-1, val.second, b[val.first-1][val.second]);
        }

        if(val.second != 0){
            childs3 = new pair(val.first, val.second-1, b[val.first][val.second-1]);
        }


        if(val.color.equals(childs1.color)){
            childArray.add(childs0);
            getChild(childs0);
        }
/*        else{
            if(childs0.color.equals("G")){
                green++;
            }
            else if(childs0.color.equals("R")){
                red++;
            }
            else if(childs0.color.equals("Y")){
                yellow++;
            }
        }*/

        if(val.color.equals(childs1.color)){
            childArray.add(childs1);
            getChild(childs1);
        }
/*        else {
            if(childs1.color.equals("R")){
                red++;
            }
            else if(childs1.color.equals("G")){
                green++;
            }
            else if(childs1.color.equals("Y")){
                yellow++;
            }
        }*/
        if(val.first!=0) {
            if (val.color.equals(childs2.color)) {
                childArray.add(childs2);
                getChild(childs2);
            }
        }
        if(val.second != 0) {
            if (val.color.equals(childs3.color)) {
                childArray.add(childs3);
                getChild(childs3);
            }
        }

        for(int i = 0; i<childArray.size();i++)
        {
            if(childArray.get(i).color == null){
                childArray.remove(i);
            }
        }
    }
}

class pair{
    int first,second;
    String color;

    pair(int a, int b, String c){
        first = a;
        second = b;
        color = c;
    }


}

class NextState{
    int red,green,yellow;
    int m = 6;
    int r,g,y;


    public NextState(int r, int g, int y) {
        this.r = r;
        this.g = g;
        this.y = y;
    }

    String generateNextColor(pair a, String[][] Blocks){
        if(a.color == "R"){
            green = countGreen(Blocks);
            yellow = countYellow(Blocks);
            if(green > yellow && Blocks[a.first][a.second + 1] == "G" || green > yellow && Blocks[a.first + 1][a.second] == "G"){
                return "G";
            }
            else
                return "Y";
        }
        else if(a.color == "G"){
            red = countRed(Blocks);
            yellow = countYellow(Blocks);
            if(red > yellow && Blocks[a.first][a.second + 1] == "G" || red > yellow && Blocks[a.first + 1][a.second] == "G"){
                return "R";
            }
            else
                return "Y";
        }
        else{
            green = countGreen(Blocks);
            red = countRed(Blocks);
            if(green > red && Blocks[a.first][a.second + 1] == "G" || green > red && Blocks[a.first + 1][a.second] == "G"){
                return "G";
            }
            else
                return "R";
        }
    }

    private int countGreen(String[][] blocks) {
        int count = 0;
        for(int i = 0; i<m;i++)
        {
            for(int j = 0; j<m; j++)
            {
                if(blocks[i][j] == "G"){
                    count++;
                }
            }
        }
        return count;
    }
    private int countRed(String[][] blocks) {
        int count = 0;
        for(int i = 0; i<m;i++)
        {
            for(int j = 0; j<m; j++)
            {
                if(blocks[i][j] == "R"){
                    count++;
                }
            }
        }
        return count;
    }
    private int countYellow(String[][] blocks) {
        int count = 0;
        for(int i = 0; i<m;i++)
        {
            for(int j = 0; j<m; j++)
            {
                if(blocks[i][j] == "Y"){
                    count++;
                }
            }
        }
        return count;
    }
}

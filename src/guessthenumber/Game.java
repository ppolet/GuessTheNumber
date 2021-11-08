package guessthenumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {
    private String number; //загаданное число
    private int userNum; //введенное число игроком
    private UserMove move; 
    private List<UserMove> historyMoves;
    
    //загадываем новое число с повторяющимися цифрами
    private void newNumberWithRepeating(){
        number = "";
        for(int i = 0; i < 4; i++){
            number = number + (int)(Math.random()*10);
        }
    }

    //загадываем новое число без повторяющихся цифр
    private void newNumberNoRepeating(){
        List<Integer> n = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
        number = "";
        for(int i = 0; i < 4; i++){
            int index = (int)(Math.random()*n.size());
            number = number + n.get(index);
            n.remove(index);
            //Set<Integer> aaa = new HashSet<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
            //aaa.remove(i);
        }
        //System.out.println("NUMBER: " + number);
    }

    //проверяем число пользователя
    private UserMove checkUserNumber(String userInput){
        int n1 = 0; 
        int n2 = 0;
        //Считаем сколько цифр в введенном игроком числе есть в загаданном n1 
        //Считаем правильность позиции цифры в загаданном числе n2
        for(int i = 0; i < 4; i++){
            if(number.contains(String.valueOf(userInput.charAt(i)))) n1++;
            if(userInput.charAt(i) == number.charAt(i)) n2++;
        }
        return new UserMove(userInput, n1, n2);
    }

    private String getUserInput(){
        String st = "";
        
        Scanner sc = new Scanner(System.in);
        st = sc.next();
        
        return st;
    }
    
    private void printCheckResult(){
        System.out.println("----------------");
        for(UserMove m: historyMoves){
            System.out.println(m.userMove + " - " + m.getCorrectDigits() + " " + m.getCorrectPlaceDigits());
        }
    }
    
    private boolean checkCorrectInput(String st){
        boolean result = false;
        try{
            Integer num = Integer.parseInt(st);
            if (num >= 0 && num <= 9999) result = true;
        } catch (Exception ex){
            result = false;
        }
        return result;
    }
    
    //ввод числа - выбора варианта игры 1 или 2, если другой символ, то выход из игры
    private int gameChoice(){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        if(sc.hasNextInt()){
            n = sc.nextInt();
        }
        return n;
    }
    
    public void startGame(){
        System.out.println(" -= Игра \"Угадай число\" =-");
        System.out.print("1 - угадать число от 0000 до 9999 с повторениями цифр\n"
                + "2 - угадать число без повторения цифр\n"
                + "Выберите игру: ");
        
        switch (gameChoice()){
            case 1: 
                System.out.println("Игра с повторяющимися цифрами в загаданном числе");
                newNumberWithRepeating();
                break;
            case 2:
                System.out.println("Игра без повторяющихся цифр в загаданном числе");
                newNumberNoRepeating();
                break;
            default:
                System.out.println("Вы не ввели 1 или 2. Выходим...");
                return;
        }

        System.out.println("");
        System.out.println("Угадай число. Введите 4-х значное число. Или слово quit для выхода");

        historyMoves = new ArrayList<>();
        
        do{
            String userInput = getUserInput();
            
            //если игрок ввел "quit" выходим из игры
            if(userInput.equals("quit")){
                System.out.println("QUIT from user");
                return;
            } 
            
            //проверяем корректность введенного игроком числа
            if(checkCorrectInput(userInput)){
                UserMove lastUserMove = checkUserNumber(userInput);
                historyMoves.add(lastUserMove);
                printCheckResult();
                if(lastUserMove.getCorrectPlaceDigits() == 4){
                    System.out.println("Вы выиграли за " + historyMoves.size() + " ходов!!!");
                    return;
                }
            } else {
                System.out.println("Не корректно ввели число, введите еще раз");
            }
        } while(true);
        
    }
}

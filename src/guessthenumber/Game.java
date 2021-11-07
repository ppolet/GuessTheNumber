package guessthenumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private String number; //загаданное число
    private int userNum; //введенное число игроком
    private UserMove move; 
    private List<UserMove> historyMoves;
    
    //загадываем новое число
    private void newNumber(){
        number = "";
        for(int i = 0; i < 4; i++){
            number = number + (int)(Math.random()*10);
        }
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
    
    public void startGame(){
        System.out.println("Угадай число. Введите 4-х значное число. Или слово quit для выхода");
        newNumber();
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

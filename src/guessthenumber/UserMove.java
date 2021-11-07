package guessthenumber;

public class UserMove {
    String userMove; //число введенное игроком
    int correctDigits; //кол-во угаданных цифр 
    int correctPlaceDigits; // кол-во правильных мест цифр

    public UserMove(String userMove, int correctDigits, int correctPlaceDigits) {
        this.userMove = userMove;
        this.correctDigits = correctDigits;
        this.correctPlaceDigits = correctPlaceDigits;
    }

    
    public String getUserMove() {
        return userMove;
    }

    public void setUserMove(String userMove) {
        this.userMove = userMove;
    }
    
    public int getCorrectDigits() {
        return correctDigits;
    }

    public void setCorrectDigits(int correctDigits) {
        this.correctDigits = correctDigits;
    }

    public int getCorrectPlaceDigits() {
        return correctPlaceDigits;
    }

    public void setCorrectPlaceDigits(int correctPlaceDigits) {
        this.correctPlaceDigits = correctPlaceDigits;
    }

    
}

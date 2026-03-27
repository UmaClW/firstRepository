
void main() {
    while (true) {
        boolean a = true;
        if (!(gameStart(a))){
            break;
        }
        String gameWord = getGameWord();
        char[] gameWordChars = gameWord.toCharArray();
        maskWord(gameWord, gameWordChars);
        printGameWord(gameWord, gameWordChars);
        int mistakesCount = 0;
        int correctLettersCount = 0;
        char[] inputLetters = new char[33];
        int k = 0;
        while (correctLettersCount < gameWord.length() && mistakesCount < 6) {
            System.out.print("Выберите букву: ");
            Scanner console = new Scanner(System.in);
            String guessLine = console.nextLine();
            char guessLetter = guessLine.toLowerCase().charAt(0);
            boolean containsLetter = gameWord.toLowerCase().contains(guessLine.toLowerCase());
            if (containsLetter) {
                if (!(new String(inputLetters).contains(guessLine))) {
                    inputLetters[k] = guessLine.charAt(0);
                    k++;
                    for (int i = 0; i < gameWord.length(); i++) {
                        if (guessLetter == gameWord.charAt(i)) {
                            gameWordChars[i] = guessLetter;
                            correctLettersCount++;
                        }
                    }
                    System.out.println("Верная буква!");
                    printGameWord(gameWord, gameWordChars);
                } else {
                    System.out.println("Нужна новая буква");
                }
                } else {
                mistakesCount++;
                System.out.println("Неверная буква! кол-во ошибок " + mistakesCount);
                printGameWord(gameWord, gameWordChars);
            }
        }
        if (!(correctLettersCount < gameWord.length())) {
            System.out.println("Поздравляю! Вы выиграли!!");
        } else
            System.out.println("Вы проиграли! Удачи в следующий раз!!");
    }
}


private static String getGameWord() {
    String[] hiddenWord = new String[]{"Машина", "Сосиска", "Страдание"};
    Random random = new Random();
    int randomIndex = random.nextInt(0, 3);
    String gameWord = hiddenWord[randomIndex];
    return gameWord.toLowerCase();
}

private static void printGameWord(String gameWord, char[] gameWordChars) {
    System.out.println("Ваше слово: ");
    for (int i = 0; i < gameWord.length(); i++) {
        System.out.print("[" + gameWordChars[i] + "]");
    }
    System.out.println("");
}

public static boolean gameStart(boolean a) {
    System.out.println("Добро пожаловать!Желаете начать игру? (введите start/stop)");
    Scanner console = new Scanner(System.in);
    String answer = console.nextLine();
    String lowRegisterAnswer = answer.toLowerCase();
    String startAnswer = "start";
    String stopAnswer = "stop";
    boolean containsStart = lowRegisterAnswer.contains(startAnswer.toLowerCase());
    boolean containsStop = lowRegisterAnswer.contains(stopAnswer.toLowerCase());
    if (containsStart) {
        System.out.println("Запуск...");
        System.out.println("Приятной игры!");
        return true;
    } else return false;



}

public static void maskWord(String gameWord, char[] gameWordChars){
    for (int i = 0; i < gameWord.length(); i++) {
        gameWordChars[i] = '_';
    }
}
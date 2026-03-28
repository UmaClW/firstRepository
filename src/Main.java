import javax.management.remote.rmi.RMIJRMPServerImpl;

void main() {
    while (true) {
        boolean a = true;
        if (!(gameStart(a))){
            break;                  // выход из игры (IDEA почем то ругается на параметр a)
        }
        int mistakesCount = 0;
        String gameWord = getGameWord();
        char[] gameWordChars = gameWord.toCharArray();
        maskWord(gameWord, gameWordChars);
        printHangman(mistakesCount);
        printGameWord(gameWord, gameWordChars);
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
                if (!(new String(inputLetters).contains(guessLine))) { // проверка на повторный ввод
                    inputLetters[k] = guessLine.charAt(0);
                    k++;
                    for (int i = 0; i < gameWord.length(); i++) {
                        if (guessLetter == gameWord.charAt(i)) {
                            gameWordChars[i] = guessLetter;
                            correctLettersCount++;
                        }
                    }
                    System.out.println("ВЕРНАЯ БУКВА!");
                    printHangman(mistakesCount);
                    printGameWord(gameWord, gameWordChars);
                } else {
                    printHangman(mistakesCount);
                    System.out.println("Нужна новая буква");
                }
                } else {
                mistakesCount++;
                System.out.println("НЕВЕРНАЯ БУКВА! КОЛ-ВО ОШИБОК " + mistakesCount);
                printHangman(mistakesCount);
                printGameWord(gameWord, gameWordChars);
            }
        }
        if (!(correctLettersCount < gameWord.length())) {
            System.out.println("Поздравляю! Вы выиграли!!");
        } else
            printEnd();
    }
}
private static void printHangman(int mistakesCount) {
    if (mistakesCount == 0) { // не стал брать switch-case (есть ли существенная разница на таком уровне?)
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |     \t\t      ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +    // визуализация требует жертв? Рисовал сам (кроме лого)
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
    if (mistakesCount == 1){
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |    O\t\t      ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
    if (mistakesCount == 2){
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |    O\t\t      ┃\n" +
                "┃      |    |             ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
    if (mistakesCount == 3){
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |    O\t\t      ┃\n" +
                "┃      |   <|             ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
    if (mistakesCount == 4){
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |    O\t\t      ┃\n" +
                "┃      |   <|>            ┃\n" +
                "┃      |                  ┃\n" +
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
    if (mistakesCount == 5){
        System.out.println(" _________________________\n" +
                "┃       ____\t\t      ┃\n" +
                "┃      |    |\t\t      ┃\n" +
                "┃      |    O\t\t      ┃\n" +
                "┃      |   <|>            ┃\n" +
                "┃      |   /              ┃\n" +
                "┃      |                  ┃\n" +
                "┃     / \\                 ┃\n" +
                "┃_________________________┃");
    }
}

private static void printEnd() {
    System.out.println(" _________________________\n" +
            "┃       ____\t\t      ┃\n" +
            "┃      |    |\t\t      ┃\n" +
            "┃      |    O\t\t      ┃\n" +
            "┃      |   <|>            ┃\n" +
            "┃      |   / \\            ┃\n" +
            "┃      |                  ┃\n" +
            "┃     / \\                 ┃\n" +
            "┃_________________________┃");
    System.out.println("Вы проиграли! Удачи в следующий раз!!");
}


private static String getGameWord() {
    String[] hiddenWord = new String[]{"Машина", "Сосиска", "Страдание"};  // Как сделать большой удобный список слов без подключений внешних файлов?
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
    System.out.println("\n");
}

public static boolean gameStart(boolean a) {
    System.out.println("hangman_logo = r\"\"\"\n" +
            "╔═════════════════════════════════════════════════════════════════════╗\n" +
            "║                                                                     ║\n" +
            "║    ██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███╗   ███╗ █████╗ ███╗   ██╗ ║\n" +
            "║    ██║  ██║██╔══██╗████╗  ██║██╔════╝ ████╗ ████║██╔══██╗████╗  ██║ ║\n" +
            "║    ███████║███████║██╔██╗ ██║██║  ███╗██╔████╔██║███████║██╔██╗ ██║ ║\n" +
            "║    ██╔══██║██╔══██║██║╚██╗██║██║   ██║██║╚██╔╝██║██╔══██║██║╚██╗██║ ║\n" +
            "║    ██║  ██║██║  ██║██║ ╚████║╚██████╔╝██║ ╚═╝ ██║██║  ██║██║ ╚████║ ║\n" +
            "║    ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ║\n" +
            "║                                                                     ║\n" +
            "║                      CLASSIC GAME                                   ║\n" +
            "╚═════════════════════════════════════════════════════════════════════╝\n" );
    System.out.println("Добро пожаловать!Желаете начать игру? (введите start/stop)");
    Scanner console = new Scanner(System.in);
    String answer = console.nextLine();
    String lowRegisterAnswer = answer.toLowerCase();
    String startAnswer = "start";
    String stopAnswer = "stop";
    boolean containsStart = lowRegisterAnswer.contains(startAnswer.toLowerCase());
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
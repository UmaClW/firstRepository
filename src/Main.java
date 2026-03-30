import java.sql.SQLOutput;

void main() {
    while (true) {
        if (!(gameStart())){
            break;                  // выход из игры (IDEA почем то ругается на параметр a)
        }
        int mistakesCount = 0;
        String gameWord = getGameWord();
        char[] gameWordChars = gameWord.toCharArray();
        maskWord(gameWord, gameWordChars);
        printHangman(mistakesCount, gameWord);
        printGameWord(gameWord, gameWordChars);
        int correctLettersCount = 0;
        char[] inputLetters = new char[33];
        int k = 0;
        Scanner console = new Scanner(System.in);
        while (correctLettersCount < gameWord.length() && mistakesCount < 6) {
            System.out.print("Выберите букву: ");
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
                    printHangman(mistakesCount, gameWord);
                    printGameWord(gameWord, gameWordChars);
                } else {
                    printHangman(mistakesCount, gameWord);
                    System.out.println("Нужна новая буква");
                }
            } else {
                mistakesCount++;
                System.out.println("НЕВЕРНАЯ БУКВА! КОЛ-ВО ОШИБОК " + mistakesCount);
                printHangman(mistakesCount, gameWord);
                printGameWord(gameWord, gameWordChars);
            }
        }
        if (!(correctLettersCount < gameWord.length())) {
            System.out.println("ПОЗДРАВЛЯЮ! ВЫ ВЫИГРАЛИ!!");
        } else
            printHangman(mistakesCount, gameWord);
    }
}
private static void printHangman(int mistakesCount,String gameWord) {
    switch (mistakesCount) {
        case (0):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |     \t\t      ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;
        case (1):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |    O\t\t      ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;

        case (2):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |    O\t\t      ┃\n" +
                    "┃      |    |             ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;
        case (3):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |    O\t\t      ┃\n" +
                    "┃      |   <|             ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;
        case (4):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |    O\t\t      ┃\n" +
                    "┃      |   <|>            ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;
        case (5):
            System.out.println(" _________________________\n" +
                    "┃       ____\t\t      ┃\n" +
                    "┃      |    |\t\t      ┃\n" +
                    "┃      |    O\t\t      ┃\n" +
                    "┃      |   <|>            ┃\n" +
                    "┃      |   /              ┃\n" +
                    "┃      |                  ┃\n" +
                    "┃     / \\                 ┃\n" +
                    "┃_________________________┃");
            break;
        case (6):
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
            System.out.println("Загаданное слово: " + gameWord);
            break;
    }
}

private static String getGameWord()  {
    String bookLink = "src/Words";
    String line;
    int count = 0;
    Random random = new Random();
    int randomIndex = random.nextInt(0, 100);
    try (BufferedReader br = new BufferedReader(new FileReader(bookLink))) {
        while ((line = br.readLine()) != null) {
            count++;
            if (count == randomIndex) break;
        }
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    return line.toLowerCase();
}

private static void printGameWord(String gameWord, char[] gameWordChars) {
    System.out.println("Ваше слово: ");
    for (int i = 0; i < gameWord.length(); i++) {
        System.out.print("[" + gameWordChars[i] + "]");
    }
    System.out.println("\n");
}

public static boolean gameStart() {
    System.out.println(
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
    System.out.println("Добро пожаловать! Желаете начать игру? (введите start/stop)");
    Scanner console = new Scanner(System.in);
    String answer = console.nextLine();
    String lowRegisterAnswer = answer.toLowerCase();
    String startAnswer = "start";
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

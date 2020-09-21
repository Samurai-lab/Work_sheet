import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static final Pattern addPattern = Pattern.compile("(?i)add");
    public static final Pattern editPattern = Pattern.compile("(?i)edit");
    public static final Pattern deletePattern = Pattern.compile("(?i)delete");
    public static void main (String[] args) {
        ArrayList<String> todoList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
      while (true) {
          System.out.println("\nКомманды для работы с ежедневником: \nLIST - выводит все дела \nADD - добавляет дело списка на определённое место, если указать номер \nEDIT - заменять дело с указанным номером \nDELETE - удалять \nBREAK - выход из ежедневника");
          System.out.println("Все ваши дела должни начинаться с нуля!!");
          System.out.println("Введите одну из вышепредставленных комманд: ");
          String str = scanner.nextLine();
          Matcher addMatcher = addPattern.matcher(str);
          Matcher editMatcher = editPattern.matcher(str);
          Matcher deleteMatcher = deletePattern.matcher(str);
          if (str.equalsIgnoreCase("list")) {
              for (int i = 0; i < todoList.size();i++) {
                  System.out.println(i + " - " + todoList.get(i));
              }
          } else  if (str.equalsIgnoreCase("break")) {
              System.out.println("Спасибо, что воспользовались услугами нашего ежедневника");
              break;
          } else if (addMatcher.find() && (str.matches("^[A-Za-z]{3}\\s[0-9]{1,4}\\s.+$") || str.matches("^[A-Za-z]{3}\\s.+$"))) {
              if (str.matches("^[A-Za-z]{3}\\s[0-9]{1,4}\\s.+$")) {
                  String[] split = str.split(" ");
                  int number = Integer.parseInt(split[1]);
                  if (number > todoList.size()) {
                      number = todoList.size();
                      String add = str.substring(split[0].length() + split[1].length() + 2);
                      todoList.add(number, add);
                  } else {
                      String add = str.substring(split[0].length() + split[1].length() + 2);
                      todoList.add(number, add);
                  }
              } else if (str.matches("^[A-Za-z]{3}\\s.+$")) {
                  String[] split = str.split(" ");
                  int number = todoList.size();
                  String add = str.substring(split[0].length() + 1);
                  todoList.add(number, add);
              }
          } else if (editMatcher.find() && (str.matches("^[a-zA-Z]{4}\\s[0-9]{1,4}\\s.+$") && todoList.size() > 0)) {
              //ввод номера редактируемого дела
              String[] split = str.split(" ");
              int number = Integer.parseInt(split[1]);
              //ввод нового дела
              if (number >= todoList.size()) {
                  number = todoList.size() - 1;
                  System.out.print(" ");
                  String add = str.substring(split[0].length() + split[1].length() + 2);
                  todoList.set(number, add);
              } else {
                  String add = str.substring(split[0].length() + split[1].length() + 2);
                  todoList.set(number, add);
              }
          } else  if (deleteMatcher.find() && (str.matches("^[a-zA-Z]{6}\\s[0-9]{1,4}") && todoList.size() > 0)) {
              String[] split = str.split(" ");
              int number = Integer.parseInt(split[1]);
              if (number >= todoList.size()) {
                  number = todoList.size() - 1;
                  todoList.remove(todoList.get(number));
              } else {
                  todoList.remove(todoList.get(number));
              }
          } else {
              System.out.println("Введите правильно и повторите попыткку");
          }
      }
    }
}
package hw4;

import exceptions.EmptyException;
import java.util.Scanner;

public final class Calc {
  private final LinkedStack<Integer> operationList = new LinkedStack<>();

  /**
   * The main function.
   *
   * @param args Not used.
   */
  public static void main(String[] args) {
    Calc calculator = new Calc();
    int operandTwo = 0;
    Scanner scanner = new Scanner(System.in);
    boolean shouldContinue = true;

    while (shouldContinue) {
      String userInput = scanner.next();
      shouldContinue = calculator.processInput(userInput, operandTwo);
    }

    scanner.close();
  }

  private boolean processInput(String userInput, int operandTwo) {
    switch (userInput) {
      case "!": return false;
      case ".": printTop();
        break;
      case "?": printStack();
        break;
      case "+":
      case "-":
      case "*":
      case "/":
      case "%":
        performOperation(userInput,operandTwo);
        break;
      default: handleInputAsInteger(userInput);
        break;
    } return true;
  }

  private void performOperation(String userInput, int operandTwo) {
    switch (userInput) {
      case "+": performAddition(operandTwo);
        break;
      case "-": performSubtraction(operandTwo);
        break;
      case "*": performMultiplication(operandTwo);
        break;
      case "/": performDivision(operandTwo);
        break;
      case "%": performModulo(operandTwo);
        break;
      default:
    }
  }

  private void printTop() {
    try {
      int frontValue = operationList.top();
      System.out.println(frontValue);
    } catch (EmptyException e) {
      System.out.println("ERROR: no values in stack");
    }
  }

  private void printStack() {
    System.out.println(operationList);
  }

  private void performAddition(int operandTwo) {
    try {
      operandTwo = operationList.top();
      operationList.pop();
      int operandOne = operationList.top();
      operationList.pop();
      operandOne += operandTwo;
      operationList.push(operandOne);
    } catch (EmptyException e) {
      operationList.push(operandTwo);
      System.out.println("ERROR: not enough operands to perform calculation");
    }
  }

  private void performSubtraction(int operandTwo) {
    try {
      operandTwo = operationList.top();
      operationList.pop();
      int operandOne = operationList.top();
      operationList.pop();
      operandOne -= operandTwo;
      operationList.push(operandOne);
    } catch (EmptyException e) {
      operationList.push(operandTwo);
      System.out.println("ERROR: not enough operands to perform calculation");
    }
  }

  private void performMultiplication(int operandTwo) {
    try {
      operandTwo = operationList.top();
      operationList.pop();
      int operandOne = operationList.top();
      operationList.pop();
      operandOne *= operandTwo;
      operationList.push(operandOne);
    } catch (EmptyException e) {
      operationList.push(operandTwo);
      System.out.println("ERROR: not enough operands to perform calculation");
    }
  }

  private void performDivision(int operandTwo) {
    try {
      operandTwo = operationList.top();
      operationList.pop();
      int operandOne = operationList.top();
      operationList.pop();
      if (operandTwo != 0) {
        operandOne /= operandTwo;
        operationList.push(operandOne);
      } else {
        operationList.push(operandOne);
        operationList.push(operandTwo);
        System.out.println("ERROR: division by 0");
      }
    } catch (EmptyException e) {
      operationList.push(operandTwo);
      System.out.println("ERROR: not enough operands to perform calculation");
    }
  }

  private void performModulo(int operandTwo) {
    try {
      operandTwo = operationList.top();
      operationList.pop();
      int operandOne = operationList.top();
      operationList.pop();
      if (operandTwo != 0) {
        operandOne %= operandTwo;
        operationList.push(operandOne);
      } else {
        operationList.push(operandOne);
        operationList.push(operandTwo);
        System.out.println("ERROR: modulo by 0");
      }
    } catch (EmptyException e) {
      operationList.push(operandTwo);
      System.out.println("ERROR: not enough operands to perform calculation");
    }
  }

  private void handleInputAsInteger(String userInput) {
    try {
      int operand = Integer.parseInt(userInput);
      operationList.push(operand);
    } catch (NumberFormatException e) {
      System.out.println("ERROR: bad token");
    }
  }
}

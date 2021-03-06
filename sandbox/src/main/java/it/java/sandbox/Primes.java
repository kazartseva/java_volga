package it.java.sandbox;

public class Primes {

  //Создаем функцию с названием isPrime, у которой один параметр - целое число n.
  // Возвращаемое значение: true (число простое), false (число не простое)
  public static boolean isPrime(int n) {

    //Начало цикла. Здесь будем проверять, целое ли число: будем делить это число на другое число меньше него.
    // Если делитель найдется, число не простое.
    // Мы заранее знаем, сколько раз нужно повторить цикл - от 2 до (n-1) раз.
    // Внутри for описывается счетчик i. Начало (int i=2), конец (i<n), изменение счетчика на каждой итерации цикла (i=i+1).
    // В фигурных скобках описывается тело цикла - те действия, которые должны происходить на каждой итерации.
    // В этих действиях обычно участвует переменная счетчик.
    for (int i = 2; i < n; i++) {
      // Вычисляем остаток от деления %
      if (n % i == 0) {
        return false;
      }

    }
    return true;
  }

  public static boolean isPrimeFast(int n) {

    //Ускоряем производительность. Любой делитель числа будет меньше, чем квадратный корень из числа.
    //Приведем корень к целому виду: добавим перед вычисление корня (int)
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      // Вычисляем остаток от деления %
      if (n % i == 0) {
        return false;
      }

    }
    return true;
  }


  public static boolean isPrimeWhile(int n) {

    // Используем WHILE вместо FOR. В этом случае три действия (инициализация,
    // проверка условия окончания и увеличение счетчика) разнесены по разным местам
    //Инициализация
    int i = 2;
    //Проверка условия окончания
    while (i < n) {
      if (n % i == 0) {
        return false;
      }
      //Действие, которое увеличивает счетчик
      i++;
    }
    return true;
  }

  public static boolean isPrimeWhile2(int n) {

    //Добавим действие проверки (n % i == 0) в проверку условия окончания
    //Инициализация
    int i = 2;
    //Проверка условия окончания
    while (i < n && n % i != 0) {
      //если условие не выполняется, увеличим счетчик
      i++;
    }
    // Проверяем, чему равно i. Если i равно n, то это простое число (return true).
    // Если i не равно n, то цикл закончился раньше, и n не простое число (return false).
    return i == n;
  }

  //Параметр - long n.
  //long --> 64 bit numbers
  //int --> 32 bit numbers
  public static boolean isPrime(long n) {

    for (long i = 2; i < n; i++) {
      // Вычисляем остаток от деления %
      if (n % i == 0) {
        return false;
      }

    }
    return true;
  }

}


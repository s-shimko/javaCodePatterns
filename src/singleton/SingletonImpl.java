package singleton;

/*
Класс, который может иметь только один экземпляр.
 */

public class SingletonImpl {
  public static void main(String[] args) {
    System.out.println("Singleton Example:");

    System.out.println("-----Init singleton for the first time------");
    Singleton singleton1 = Singleton.getInstance();
    singleton1.printSingleton();

    System.out.println("-----Init singleton for the second time------");
    Singleton singleton2 = Singleton.getInstance();
    singleton2.printSingleton();

    System.out.println(singleton1.equals(singleton2));
  }
}

class Singleton {

  private static Singleton instance;

  private Singleton() {
  }

  static Singleton getInstance() {
    if (instance == null) instance = new Singleton();
    else System.out.println("Singleton already created");
    return instance;
  }

  void printSingleton() {
    System.out.println(instance);
  }
}




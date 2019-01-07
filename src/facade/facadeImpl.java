package facade;

/*
позволяющий скрыть сложность системы путём сведения всех возможных внешних вызовов к одному объекту,
делегирующему их соответствующим объектам системы.

Фасад — это внешний объект, обеспечивающий единственную точку входа для служб подсистемы.
Реализация других компонентов подсистемы закрыта и не видна внешним компонентам.
Фасадный объект обеспечивает реализацию GRASP паттерна Устойчивый к изменениям (Protected Variations)
с точки зрения защиты от изменений в реализации подсистемы.
 */


public class facadeImpl {
  public static void main(String[] args) {
    Facade facade = new Facade();
    facade.doLogin();
  }
}

class Facade {
  private LoginPage loginPage = new LoginPage();
  private User user = new User();

  void doLogin(){
    loginPage.open();
    user.fillLoginForm(loginPage);
    user.fillPasswordForm(loginPage);
    user.clickSubmitButton();
  }
}

class LoginPage {
  private boolean isLoginPageOpen = false;

  void open(){
    System.out.println("Login page is opened");
    isLoginPageOpen = true;
  }

  boolean isOpen(){
    return isLoginPageOpen;
  }

}

class User {
  private boolean isLoginFilled = false;
  private boolean isPasswordFilled = false;

  void fillLoginForm(LoginPage loginPage) {
    if(loginPage.isOpen()) {
      System.out.println("Login form is filled");
      isLoginFilled = true;
    } else {
      System.out.println("Login page is not opened");
    }
  }

  void fillPasswordForm(LoginPage loginPage) {
    if(loginPage.isOpen()) {
      System.out.println("Password form is filled");
      isPasswordFilled = true;
    } else {
      System.out.println("Login page is not opened");
    }
  }

  void clickSubmitButton() {
    if (isLoginFilled && isPasswordFilled) {
      System.out.println("Submit success");
    } else {
      System.out.println("Submit fail - fill all forms");
    }
  }
}



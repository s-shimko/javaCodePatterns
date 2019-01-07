package strategy;

/*
позволяет менять выбранный алгоритм независимо от объектов-клиентов, которые его используют
 */

public class StrategyImpl {
  public static void main(String[] args) {
    Browser browser = new Browser();
    Browser.Element elementCss = browser.findElement(new Css());
    elementCss.click();
    elementCss.type("first name");
    Browser.Element elementXpath = browser.findElement(new Xpath());
    elementXpath.click();
    elementXpath.type("last name");
  }
}

interface Selector {
  String chooseSelector();
}

class Css implements Selector {
  @Override
  public String chooseSelector() {
    System.out.println("CSS selector was choosed");
    return "CSS";
  }
}

class Xpath implements Selector {
  @Override
  public String chooseSelector() {
    System.out.println("XPATH selector was choosed");
    return "XPATH";
  }
}

class Browser {
  private Selector selector;

  public Element findElement(Selector selector){
    this.selector = selector;
    return new Element();
  }

  class Element {
    public void click(){
      System.out.println("Element finded with " + selector.chooseSelector() + ". Action - click");
    }

    public void type(String s){
      System.out.println("Element finded with " + selector.chooseSelector() + ". Type in element " + s);
    }
  }


}


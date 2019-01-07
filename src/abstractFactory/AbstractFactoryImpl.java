package abstractFactory;

/*
предоставляет интерфейс для создания семейств взаимосвязанных или взаимозависимых объектов,
не специфицируя их конкретных классов. Шаблон реализуется созданием абстрактного класса Factory,
который представляет собой интерфейс для создания компонентов системы (например,
для оконного интерфейса он может создавать окна и кнопки).
Затем пишутся классы, реализующие этот интерфейс
 */

public class AbstractFactoryImpl {
  public static void main(String[] args) {
    System.out.println(SiteCreator.makeFactory(SiteCreator.PageType.CARS).createDB().getTitle());
    System.out.println(SiteCreator.makeFactory(SiteCreator.PageType.CARS).createPage().getTitle());
    System.out.println(SiteCreator.makeFactory(SiteCreator.PageType.TOYS).createDB().getTitle());
    System.out.println(SiteCreator.makeFactory(SiteCreator.PageType.TOYS).createPage().getTitle());
  }

  public static class SiteCreator {
    public enum PageType {
      CARS, TOYS
    }

    static SiteFactory makeFactory(PageType type) {
      switch (type) {
        case CARS:
          return new CarSiteFactory();
        case TOYS:
          return new ToySiteFactory();
        default:
          throw new IllegalArgumentException("This issue is not supported!");
      }
    }
  }
}

interface SiteFactory {
  Page createPage();
  DataBase createDB();
}

class CarSiteFactory implements SiteFactory {
  @Override
  public MainPage createPage() {
    System.out.println("Main Page created");
    return new MainPage();
  }

  @Override
  public DataBase createDB() {
    System.out.println("Data Base for Cars site created");
    return new CarDataBase();
  }
}

class ToySiteFactory implements SiteFactory {
  @Override
  public ContactPage createPage() {
    System.out.println("Contact Page created");
    return new ContactPage();
  }

  @Override
  public DataBase createDB() {
    System.out.println("Data Base for Toys site created");
    return new ToyDataBase();
  }
}

interface Page {
  String getTitle();
}

interface DataBase {
  String getTitle();
}

class MainPage implements Page {
  @Override
  public String getTitle() {
    return "Main Page";
  }
}

class ContactPage implements Page {
  @Override
  public String getTitle() {
    return "Contact Page";
  }
}

class CarDataBase implements DataBase {
  @Override
  public String getTitle() {
    return "Data Base for Cars";
  }
}

class ToyDataBase implements DataBase {
  @Override
  public String getTitle() {
    return "Data Base for Toy";
  }
}

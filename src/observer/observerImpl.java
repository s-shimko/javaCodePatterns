package observer;

/*
Реализует у класса механизм, который позволяет объекту этого класса получать
оповещения об изменении состояния других объектов, и тем самым наблюдать за ними
*/

import java.util.ArrayList;
import java.util.List;

public class observerImpl {
  public static void main(String[] args) {
    JenkinsServer jenkinsServer = new JenkinsServer();

    Autotester gosha = new Autotester("Gosha");
    Autotester alesha = new Autotester("Alesha");
    jenkinsServer.addObserver(gosha);
    jenkinsServer.failIsHappen("ERROR: Cannot find element");
    jenkinsServer.addObserver(alesha);
    jenkinsServer.failIsHappen("ERROR: Stale element Exception");

    jenkinsServer.removeObserver(gosha);
    jenkinsServer.removeObserver(alesha);
    jenkinsServer.failIsHappen("ERROR: This error no one will see");
  }
}

interface JenkinsLaunchesObserver {
  void checkFail(String s);
}


interface Observerable {
  void addObserver(JenkinsLaunchesObserver o);
  void removeObserver(JenkinsLaunchesObserver o);
  void notifyObserver();
}

class Autotester implements JenkinsLaunchesObserver {
  String name;

  public Autotester(String name) {
    this.name = name;
  }

  @Override
  public void checkFail(String fail) {
    System.out.println(name + ", we have fail:\n" + fail + "\n----------");
  }
}

class JenkinsServer implements Observerable {
  private String fail;
  private List<JenkinsLaunchesObserver> autotesters = new ArrayList<JenkinsLaunchesObserver>();

  public void failIsHappen(String s){
    fail = s;
    notifyObserver();
  }

  @Override
  public void addObserver(JenkinsLaunchesObserver o) {
    this.autotesters.add(o);
  }

  @Override
  public void removeObserver(JenkinsLaunchesObserver o) {
    this.autotesters.remove(o);
  }

  @Override
  public void notifyObserver() {
    for(JenkinsLaunchesObserver o : autotesters) {
      o.checkFail(fail);
    }
  }

}





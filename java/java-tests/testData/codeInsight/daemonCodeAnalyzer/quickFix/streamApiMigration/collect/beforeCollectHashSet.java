// "Replace with collect" "true"
import java.util.*;

public class Collect {
  class Person {
    String getName() {
      return "";
    }
  }

  void collectNames(List<Person> persons){
    Set<String> names = new HashSet/*valuable comment*/<>();
    for (Person person : pers<caret>ons) {
      names.add(person.getName());
    }
  }
}

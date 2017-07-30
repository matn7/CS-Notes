package beyond_java_basics.interfaces_inheritance_objects;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 30.07.2017.
 */
public class HR {

    // List interface type reference
    // ArrayList - concrete typr reference
    private List<TeamMember> teamMemberList = new ArrayList<>();

    public void add(TeamMember teamMember) {
        teamMemberList.add(teamMember);
    }

    public void remove(TeamMember teamMember) {
        teamMemberList.remove(teamMember);
    }

    public void listAllMembers() {
        // forEach - Java 8, represent each element of list
        // System.out::println - Java 8, use println method from System.out, use this method on each element
        teamMemberList.forEach(System.out::println);
    }

    public void payAllMembers() {
        // Enchanced for loop
        for (TeamMember t : teamMemberList) {
            // NumberFormat - Java 7 abstract class
            // getConcurrencyInstance() - factory method
            System.out.printf("Paying %s %s %n", t.getName(), NumberFormat.getCurrencyInstance().format(t.getPay()));
        }
    }
}

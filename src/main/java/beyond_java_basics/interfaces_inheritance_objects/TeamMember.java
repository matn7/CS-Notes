package beyond_java_basics.interfaces_inheritance_objects;


import java.time.LocalDate;

/**
 * Created by Mati on 30.07.2017.
 */
public abstract class TeamMember {

    public static final String DEFAULT_NAME = "TeamMember Name";
    private static int nextId;

    private Integer id;
    private String name;
    private LocalDate joinDate;

    public TeamMember() {
        this(DEFAULT_NAME);
    }

    public TeamMember(String name) {
        id = nextId++;
        this.name = name;
        joinDate = LocalDate.now();

    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        TeamMember.nextId = nextId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Different child class for this method
    public abstract double getPay();

    @Override
    public String toString() {
        return String.format("TeamMemeber [id=%s, name=%s, joinDate=%s]", this.id, this.name, this.joinDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // if object are the same very fast
        if (!(o instanceof TeamMember)) return false; // id Object o is other type than TypeMember false
        TeamMember teamMember = (TeamMember) o;
        if (id != null ? !id.equals(teamMember.id) : teamMember.id != null) return false;
        if (name != null ? !name.equals(teamMember.name) : teamMember.name != null) return false;
        return joinDate != null ? !joinDate.equals(teamMember.joinDate) : teamMember.joinDate != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (joinDate != null ? joinDate.hashCode() : 0);
        return result;
    }

}

package reflection;

import java.util.Objects;

public class Member {
    private String name;
    private Integer age;
    private String email;

    public Member(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getName(), member.getName()) && Objects.equals(getAge(), member.getAge()) && Objects.equals(getEmail(), member.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getEmail());
    }
}

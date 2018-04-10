package org;

import java.util.*;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class Cell {
    private String name;
    private List<Member> memberList;

    public Cell(String name) {
        this.name = name;
        this.memberList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void addMember(Member member) {
        this.memberList.add(member);
    }

    public void addMembers(Collection<Member> members) {
        this.memberList.addAll(members);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return getName() != null ? getName().equals(cell.getName()) : cell.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return name + "순";
    }
}

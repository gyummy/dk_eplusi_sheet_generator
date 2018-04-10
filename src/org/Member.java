package org;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class Member {
    private String name;
    private String peer;
    private String column;

    public Member(String name) {
        this.name = name;
        this.peer = "N/A";
        this.column = "N/A";
    }

    public Member(String name, String peer) {
        this.name = name;
        this.peer = peer;
        this.column = "N/A";
    }

    public Member(String name, String peer, String column) {
        this.name = name;
        this.peer = peer;
        this.column = column;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeer() {
        return peer;
    }

    public void setPeer(String peer) {
        this.peer = peer;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (getName() != null ? !getName().equals(member.getName()) : member.getName() != null) return false;
        return getPeer() != null ? getPeer().equals(member.getPeer()) : member.getPeer() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getPeer() != null ? getPeer().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + peer;
    }
}

package org;

/**
 * Created by Gyummy on 2018-04-10.
 *
 */
public class Member {
    private String name;
    private String peer;
    private String rawColumn;
    private String tempColumn;

    public Member(String name) {
        this.name = name;
        this.peer = "N/A";
        this.rawColumn = "N/A";
    }

    public Member(String name, String peer) {
        this.name = name;
        this.peer = peer;
        this.rawColumn = "N/A";
    }

    public Member(String name, String peer, String rawColumn) {
        this.name = name;
        this.peer = peer;
        this.rawColumn = rawColumn;
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

    public String getRawColumn() {
        return rawColumn;
    }

    public void setRawColumn(String rawColumn) {
        this.rawColumn = rawColumn;
    }

    public String getTempColumn() {
        return tempColumn;
    }

    public void setTempColumn(String tempColumn) {
        this.tempColumn = tempColumn;
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

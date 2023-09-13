package com.bibliotheque.entity;

import java.util.List;
import java.util.Objects;

public class Membre {
    private Integer memberNémuro;
    private String memberNom;
    private Integer memberTelephon;
    private String memberCin;
    private List<Emprunt> emprunts;

    public Membre() {
    }

    public Membre(Integer memberNémuro, String memberNom, Integer memberTelephon, String memberCin, List<Emprunt> emprunts) {
        this.memberNémuro = memberNémuro;
        this.memberNom = memberNom;
        this.memberTelephon = memberTelephon;
        this.memberCin = memberCin;
        this.emprunts = emprunts;
    }

    public Integer getMemberNémuro() {
        return memberNémuro;
    }

    public void setMemberNémuro(Integer memberNémuro) {
        this.memberNémuro = memberNémuro;
    }

    public String getMemberNom() {
        return memberNom;
    }

    public void setMemberNom(String memberNom) {
        this.memberNom = memberNom;
    }

    public Integer getMemberTelephon() {
        return memberTelephon;
    }

    public void setMemberTelephon(Integer memberTelephon) {
        this.memberTelephon = memberTelephon;
    }

    public String getMemberCin() {
        return memberCin;
    }

    public void setMemberCin(String memberCin) {
        this.memberCin = memberCin;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membre membre = (Membre) o;
        return Objects.equals(memberNémuro, membre.memberNémuro) && Objects.equals(memberNom, membre.memberNom) && Objects.equals(memberTelephon, membre.memberTelephon) && Objects.equals(memberCin, membre.memberCin) && Objects.equals(emprunts, membre.emprunts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNémuro, memberNom, memberTelephon, memberCin, emprunts);
    }

    @Override
    public String toString() {
        return "Membre{" +
                "memberNémuro=" + memberNémuro +
                ", memberNom='" + memberNom + '\'' +
                ", memberTelephon=" + memberTelephon +
                ", memberCin='" + memberCin + '\'' +
                ", emprunts=" + emprunts +
                '}';
    }
}

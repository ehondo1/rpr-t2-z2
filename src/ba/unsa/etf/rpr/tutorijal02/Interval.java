package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna;
    private double krajnja;
    private boolean pocetna_pripada_intervalu;
    private boolean krajnja_pripada_intervalu;

    public Interval() {
        this.pocetna=0;
        this.krajnja=0;
        this.pocetna_pripada_intervalu=false;
        this.krajnja_pripada_intervalu=false;
    }

    public Interval(double pocetna,double krajnja,boolean pocetna_pripada_intervalu,boolean krajnja_pripada_intervalu) {
        if(pocetna>krajnja) throw new IllegalArgumentException("Pocetna tacka je veca od krajnje");
        this.pocetna=pocetna;
        this.krajnja=krajnja;
        this.pocetna_pripada_intervalu=pocetna_pripada_intervalu;
        this.krajnja_pripada_intervalu=krajnja_pripada_intervalu;
    }

    public boolean isNull() {
        if(pocetna==0 && krajnja==0 && !pocetna_pripada_intervalu && !krajnja_pripada_intervalu)
            return true;
        return false;
    }

    public boolean isIn(double tacka) {
        if(pocetna_pripada_intervalu && krajnja_pripada_intervalu) {
            if(tacka>=pocetna && tacka<=krajnja)
                return true;
        }
        else if(pocetna_pripada_intervalu && !krajnja_pripada_intervalu) {
            if(tacka>=pocetna && tacka<krajnja)
                return true;
        }
        else if(!pocetna_pripada_intervalu && krajnja_pripada_intervalu) {
            if(tacka>pocetna && tacka<=krajnja)
                return true;
        }
        else if(!pocetna_pripada_intervalu && !krajnja_pripada_intervalu){
            if(tacka>pocetna && tacka<krajnja)
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        Interval i=(Interval) o;
        if(pocetna==i.pocetna && krajnja==i.krajnja && pocetna_pripada_intervalu==i.pocetna_pripada_intervalu && krajnja_pripada_intervalu==i.krajnja_pripada_intervalu)
            return true;
        return false;
    }
    @Override
    public String toString() {
        String prazan="()";
        if(isNull())
            return prazan;
        String nije_prazan="";
        if(pocetna_pripada_intervalu){
                nije_prazan+="[";
        }
        if(!pocetna_pripada_intervalu) {
            nije_prazan+="(";
        }
        nije_prazan+=pocetna+","+krajnja;
        if(krajnja_pripada_intervalu) {
            nije_prazan+="]";
        }
        if(!krajnja_pripada_intervalu) {
            nije_prazan+=")";
        }
        return nije_prazan;
    }
    public  Interval intersect(Interval interval) {

        double nova_poc=0;
        double nova_kraj=0;
        boolean nova_poc_pripada=false;
        boolean nova_kraj_pripada=false;

        double pocetna_tacka=pocetna;
        double krajnja_tacka=krajnja;
        boolean poc_pripada=pocetna_pripada_intervalu;
        boolean kraj_pripada=krajnja_pripada_intervalu;

        if(pocetna_tacka>interval.pocetna) {
            nova_poc=pocetna_tacka;
            if(poc_pripada)
                nova_poc_pripada=true;
            if(krajnja_tacka>interval.krajnja) {
                nova_kraj=interval.krajnja;
                if(interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;

            }
            else if(krajnja_tacka<interval.krajnja) {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada)
                    nova_kraj_pripada=true;
            }
            else {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada || interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;
            }
        }
        else if(pocetna_tacka<interval.pocetna)
        {
            nova_poc=interval.pocetna;
            if(interval.pocetna_pripada_intervalu)
                nova_poc_pripada=true;
            if(krajnja_tacka>interval.krajnja) {
                nova_kraj=interval.krajnja;
                if(interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;

            }
            else if(krajnja_tacka<interval.krajnja) {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada)
                    nova_kraj_pripada=true;
            }
            else {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada || interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;
            }
        }
        else if(pocetna==interval.pocetna)
        {
            nova_poc=interval.pocetna;
            if(interval.pocetna_pripada_intervalu || poc_pripada)
                nova_poc_pripada=true;
            if(krajnja_tacka>interval.krajnja) {
                nova_kraj=interval.krajnja;
                if(interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;

            }
            else if(krajnja_tacka<interval.krajnja) {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada)
                    nova_kraj_pripada=true;
            }
            else {
                nova_kraj=krajnja_tacka;
                if(kraj_pripada || interval.krajnja_pripada_intervalu)
                    nova_kraj_pripada=true;
            }
        }
        Interval interval1=new Interval(nova_poc,nova_kraj,nova_poc_pripada,nova_kraj_pripada);
       return interval1;

    }
    public static Interval intersect(Interval interval1,Interval interval2) {
        Interval i3=interval1.intersect(interval2);
        return i3;
    }


}

package model;


/** Un Etudiant possède: un nom, un age, une promo, une filière**/
public class Etudiant {

    public String name;

    public Integer age;

    public Integer promo;

    public enum Filiere { F1, F2, F3, F4, F5}

    public Filiere filiere;

    /** CONSTRUCTEUR **/

    public Etudiant() {
    }

    public Etudiant(String name, Integer age, Filiere filiere) {
        this.name = name;
        this.age = age;
        this.filiere = filiere;
    }

    /** GETTER **/

    public String getName() { return name; }

    public Integer getAge() { return age; }

    public Integer getPromo(){ return promo; }

    public Filiere getFiliere(){ return filiere; }

    /** SETTER **/

    public void setName(String name) { this.name = name; }

    public void setAge(Integer age) { this.age = age; }

    public void setPromo(Integer promo){ this.promo = promo; }

    public void setFiliere(Filiere filiere){ this.filiere = filiere; }

}
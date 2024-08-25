package mk.ukim.studentdormsapp.model.enumeration;

public enum Faculties {
    AGRICULTURAL_SCIENCES_AND_FOOD("Faculty of Agricultural Sciences and Food"),
    ARCHITECTURE("Faculty of Architecture"),
    CIVIL_ENGINEERING("Faculty of Civil Engineering"),
    COMPUTER_SCIENCE_AND_ENGINEERING("Faculty of Computer Science and Engineering"),
    DENTISTRY("Faculty of Dentistry"),
    DESIGN_AND_TECHNOLOGIES_OF_FURNITURE_AND_INTERIOR("Faculty of Design and Technologies of Furniture and Interior"),
    DRAMATIC_ARTS("Faculty of Dramatic Arts"),
    ECONOMICS("Faculty of Economics"),
    ELECTRICAL_ENGINEERING_AND_INFORMATION_TECHNOLOGIES("Faculty of Electrical Engineering and Information Technologies"),
    FINE_ARTS("Faculty of Fine Arts"),
    MECHANICAL_ENGINEERING("Faculty of Mechanical Engineering"),
    MEDICINE("Faculty of Medicine"),
    MUSIC("Faculty of Music"),
    NATURAL_SCIENCES_AND_MATHEMATICS("Faculty of Natural Sciences and Mathematics"),
    PHARMACY("Faculty of Pharmacy"),
    PHILOSOPHY("Faculty of Philosophy"),
    PHYSICAL_EDUCATION_SPORT_AND_HEALTH("Faculty of Physical Education, Sport and Health"),
    TECHNOLOGY_AND_METALLURGY("Faculty of Technology and Metallurgy"),
    VETERINARY_MEDICINE("Faculty of Veterinary Medicine"),
    IUSTINIANUS_PRIMUS_FACULTY_OF_LAW("Iustinianus Primus Faculty of Law"),
    ST_KLIMENT_OHRIDSKI_FACULTY_OF_PEDAGOGY("St. Kliment Ohridski Faculty of Pedagogy");

    public final String displayName;

    Faculties(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
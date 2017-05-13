package objects;

public class Pair {
    private final int year;
    private final int semester;

    public Pair(int year, int semester) {
        this.year = year;
        this.semester = semester;
    }

    public int getYear() { return year; }
    public int getSemester() { return semester; }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + semester;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        if (year != pair.year) return false;
        return semester == pair.semester;
    }
}

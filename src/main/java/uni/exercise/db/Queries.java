package uni.exercise.db;

public enum Queries {

    ADD_USER("INSERT INTO {0} (username, password, address, gmail) VALUES(?, ?, ?, ?)"),
    RETRIEVE_DETAILS("SELECT * FROM {0} WHERE username = ?");


    private final String query;

    Queries(String query) {
        this.query = query;
    }

    public String get_query() {
        return this.query;
    }

}
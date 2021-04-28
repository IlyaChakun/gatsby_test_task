package by.chakun.helper;


public class IdGenerator {

    public static Long getRandomId() {
        final long leftLimit = 1L;
        final long rightLimit = Long.MAX_VALUE / 2;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}

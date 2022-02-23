package store;

import models.Item;

public class Test {
    public static void main(String[] args) {
        DbStore.instOf().findAll().stream().forEach(System.out::println);
    }
}

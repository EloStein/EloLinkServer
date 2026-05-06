package de.spring.elolink_spring.test;

public class BuilderTestMain {
    public static void main(String[] args) {
        EntityBuilder e = EntityBuilder.builder()
                .firstName("Elo")
                .lastName("Stein")
                .build();

        System.out.println(e.getPhone());

    }

}

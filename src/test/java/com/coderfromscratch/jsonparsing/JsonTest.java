package com.coderfromscratch.jsonparsing;

import com.coderfromscratch.jsonparsing.pojo.AuthorPOJO;
import com.coderfromscratch.jsonparsing.pojo.BookPOJO;
import com.coderfromscratch.jsonparsing.pojo.DayPOJO;
import com.coderfromscratch.jsonparsing.pojo.SimpleTestCaseJsonPOJO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{  \n" +
            "  \"title\": \"Coder From Scratch\",  \n" +
            "  \"author\": \"Rui\"\n" +
            "}";

    private String dayScenario1 = "{\n" +
            "  \"date\": \"2019-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";

    private String authorBookScenario= "{\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"inPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"inPrint\": false,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    void parse() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText() , "Coder From Scratch");

    }

    @Test
    void fromJson() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCaseJsonPOJO pojo = Json.fromJson(node , SimpleTestCaseJsonPOJO.class);

        assertEquals(pojo.getTitle() , "Coder From Scratch");

    }

    @Test
    void toJson() {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        assertEquals(node.get("title").asText() , "Testing 123");
    }

    @Test
    void stingify() throws JsonProcessingException {
        SimpleTestCaseJsonPOJO pojo = new SimpleTestCaseJsonPOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        System.out.println(Json.stingify(node));
        System.out.println(Json.prettyPrint(node));

    }

    @Test
    void dayTestScenario1() throws IOException {

        JsonNode node = Json.parse(dayScenario1);
        DayPOJO pojo = Json.fromJson(node , DayPOJO.class);

        assertEquals("2019-12-25", pojo.getDate().toString());
    }

    @Test
    void authorBookScenario1() throws IOException {

        JsonNode node = Json.parse(authorBookScenario);
        AuthorPOJO pojo = Json.fromJson(node , AuthorPOJO.class);

        System.out.println("Author : " + pojo.getAuthorName());
        for (BookPOJO bP : pojo.getBooks()) {
            System.out.println("Book : " + bP.getTitle());
            System.out.println("Is In Print? " + bP.isInPrint());
            System.out.println("Date : " + bP.getPublishDate());
        }
    }
}
package friends;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class CliqueTest {
    private static String resourceRoot = "./src/test/resources/";
    private static class TestCase {
        public String graphFile;
        public String school;
        public HashSet<HashSet<String>> answers;

        public TestCase(String graphFile, String school, String[][] answers) {
            this.graphFile = graphFile;
            this.school = school;
            this.answers = new HashSet<>();
            for (String[] answer : answers) {
                this.answers.add(new HashSet<>(Arrays.asList(answer)));
            }
        }
    }

    TestCase[] testCases = {
            new TestCase(
                    "subtest1_2.txt",
                    "cornell",
                    new String[][] {}
            ),
            new TestCase(
                    "subtest1_2.txt",
                    "rutgers",
                    new String[][] {{"kaitlin"}}
            ),
            new TestCase(
                    "subtest3.txt",
                    "rutgers",
                    new String[][] {{"sara"}, {"kaitlin"}}
            ),
            new TestCase(
                    "clqtest4.txt",
                    "rutgers",
                    new String[][] {{"p1", "p2", "p3", "p4"}}
            ),
            new TestCase(
                    "assnsample.txt",
                    "rutgers",
                    new String[][] { {"sam", "jane", "bob", "kaitlin"}, {"sergei", "aparna"} }
            ),
            new TestCase(
                    "subtest5.txt",
                    "rutgers",
                    new String[][] {{"p3", "p104", "p4", "p204"}, {"p98", "p199", "p99", "p299"}}
            )
    };

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testCliques(int testCase) throws IOException {
        var currentTestCase = testCases[testCase];
        Graph g = new Graph(new Scanner(new File(resourceRoot + currentTestCase.graphFile)));
        var cliques = Friends.cliques(g, currentTestCase.school);

        if (currentTestCase.answers.isEmpty()) {
            if (cliques == null) return;
            Assertions.assertThat(cliques).isEmpty();
            return;
        }
        
        Assertions.assertThat(cliques).isNotNull();

        for (ArrayList<String> clique : cliques) {
            HashSet<String> cliqueSet = new HashSet<>(clique);
            Assertions.assertThat(cliqueSet).isIn(currentTestCase.answers);
        }
    }
}

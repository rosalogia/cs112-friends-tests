package friends;

import org.assertj.core.api.Assertions;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ShortestChainTest {
    private static String resourceRoot = "./src/test/resources/";
    private static class TestCase {
        public String graphFile;
        public String src;
        public String tgt;
        public ArrayList<ArrayList<String>> answers;

        public TestCase(String graphFile, String src, String tgt, String[][] answers) {
            this.graphFile = graphFile;
            this.src = src;
            this.tgt = tgt;
            this.answers = new ArrayList<>();
            for (String[] answer : answers) {
                this.answers.add(new ArrayList<>(Arrays.asList(answer)));
            }
        }
    }

    TestCase[] testCases = {
            new TestCase(
                    "sptest1.txt",
                    "aparna",
                    "kaitlin",
                    new String[][]{}
            ),
            new TestCase(
                    "subtest3.txt",
                    "kaitlin",
                    "nick",
                    new String[][]{{"kaitlin", "nick"}}
            ),
            new TestCase(
                    "assnsample.txt",
                    "nick",
                    "aparna",
                    new String[][]{{"nick", "ricardo", "aparna"}}
            ),
            new TestCase(
                    "sptest4.txt",
                    "p1",
                    "p50",
                    new String[][]{{"p1", "p49", "p50"}, {"p1", "p51", "p50"}}
            ),
            new TestCase(
                    "subtest5.txt",
                    "p1",
                    "p10",
                    new String[][] { {"p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10"} }

            ),
            new TestCase(
                    "subtest5.txt",
                    "p301",
                    "p198",
                    new String[][]{{"p301", "p100", "p99", "p98", "p198"}}
            )
    };

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testShortestPath(int testCase) throws IOException {
        var currentTestCase = testCases[testCase];
        Graph g = new Graph(new Scanner(new File(resourceRoot + currentTestCase.graphFile)));
        var shortestChain = Friends.shortestChain(g, currentTestCase.src, currentTestCase.tgt);

        if (currentTestCase.answers.isEmpty()) {
            if (shortestChain == null) return;
            Assertions.assertThat(shortestChain).isEmpty();
            return;
        }

        Assertions.assertThat(shortestChain).isIn(currentTestCase.answers);
    }
}

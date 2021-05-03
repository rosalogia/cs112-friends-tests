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

public class ConnectorTest {
    private static String resourceRoot = "./src/test/resources/";
    private static class TestCase {
        public String graphFile;
        public HashSet<String> answers;

        public TestCase(String graphFile, String[] answers) {
            this.graphFile = graphFile;
            this.answers = new HashSet<>(Arrays.asList(answers));
        }
    }

    TestCase[] testCases = {
            new TestCase(
                    "subtest1_2.txt",
                    new String[] {}
            ),
            new TestCase(
                    "clqtest4.txt",
                    new String[] {}
            ),
            new TestCase(
                    "subtest3.txt",
                    new String[] { "nick" }
            ),
            new TestCase(
                    "subtest4.txt",
                    new String[] { "p1" }
            ),
            new TestCase(
                    "assnsample.txt",
                    new String[] { "jane", "aparna", "nick", "tom", "michele"}
            ),
            new TestCase(
                    "conntest6.txt",
                    new String[] { "p2", "p3", "p4" }
            )
    };

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testConnectors(int testCase) throws IOException {
        var currentTestCase = testCases[testCase];
        Graph g = new Graph(new Scanner(new File(resourceRoot + currentTestCase.graphFile)));
        var connectors = Friends.connectors(g);

        if (currentTestCase.answers.isEmpty()) {
            if (connectors == null) return;
            Assertions.assertThat(connectors).isEmpty();
            return;
        }

        Assertions.assertThat(connectors).isNotNull();
        Assertions.assertThat(new HashSet<>(connectors)).isEqualTo(currentTestCase.answers);
    }
}

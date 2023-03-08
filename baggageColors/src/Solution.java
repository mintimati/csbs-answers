import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args) {

		Solution.baggageColors("baggageColors/src/inputfile");

	}


	public static void baggageColors(String path) {
		try {
			var amount = Files
					.lines(Paths.get(path))
					.filter(e -> e.contains("shiny"))
					.map(e -> {
						String patt = "\\d?\\s?shiny gold bag[s]?";
						Pattern r = Pattern.compile(patt);
						Matcher m = r.matcher(e);
						if (m.find()) {
							e = m.group(0);
						}
						return e;
					})
					.map(e -> new StringBuilder(e.toString()))
					.map(e -> e.delete(1, e.length()))
					.map(e -> {
						try {
							var bagsinLine = Integer.parseInt(e.toString());
							return bagsinLine;
						}
						catch (NumberFormatException xl) {
							return 1;
						}
					})
					.reduce(0, (subtotal, element) -> subtotal + element);

			System.out.println(amount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

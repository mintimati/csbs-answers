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
		//accessing the "inputfile" via stream
		try {
			var amount = Files
					.lines(Paths.get(path))
					.filter(e -> e.contains("shiny"))
					.map(e -> {
						// we look for shiny gold bags, with MAYBE an int before them
						String patt = "\\d?\\s?shiny gold bag[s]?";
						Pattern r = Pattern.compile(patt);
						Matcher m = r.matcher(e);
						if (m.find()) {
							e = m.group(0);
						}
						return e;
					})

					/*
					we turn the results, in this case the following:
					1 shiny gold bag
					shiny gold bag
					2 shiny gold bag

					into stringbuilders so they're mutable, and then we reduce them to their
					first character, so we can learn how many of them are there per line (if there
					is no integer, we must assume there is only one.
					 */

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
					//summing the amount of them up
					.reduce(0, (subtotal, element) -> subtotal + element);

			System.out.println(amount);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

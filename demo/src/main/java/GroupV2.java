import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupV2 {
	public static void main(String[] args) {
		Map<String, Long> m1 = new HashMap<>();
		m1.put("A", 1l);
		m1.put("B", 100l);

		Map<String, Long> m2 = new HashMap<>();
		m2.put("A", 10l);
		m2.put("B", 20l);
		m2.put("C", 100l);

		List<Map<String, Long>> beforeFormatting = new ArrayList<>();
		beforeFormatting.add(m1);
		beforeFormatting.add(m2);

		Map<String, List<Long>> afterFormatting = beforeFormatting.stream().flatMap(m -> m.entrySet().stream()).collect(
				Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

		System.out.println(afterFormatting); // prints {A=[1, 10], B=[100, 20], C=[100]}
	}
}

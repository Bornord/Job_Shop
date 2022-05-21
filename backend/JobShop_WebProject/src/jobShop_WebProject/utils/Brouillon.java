package jobShop_WebProject.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Brouillon {
	public static class ListTest{
		private List<Integer> list ;
		public ListTest() {
			list =  new ArrayList<Integer>();
			for (int i = 0; i < 10; i++) {
				list.add(i);
			}
		}
		public List<Integer> getList() {
			return list;
		}
	}
	public static void testToList() {
		ListTest list = new ListTest();
		String json = JsonConverter.toJson(list);
		System.out.println(json);
		Map<String, Object> map = JsonConverter.toObject(json);
		System.out.println(map);
	}
}

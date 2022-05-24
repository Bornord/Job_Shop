package jobShop_WebProject.utils;

import java.io.Serializable;
import java.lang.reflect.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JsonConverter {
	//private Object object;
	
	/*public JsonConverter(Object o) {
		this.object = o;
	}*/

	public JsonConverter() {

	}
	
	public static String withoutAccolade(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String res = "";
		Class classO = object.getClass();
		Field[] fields = classO.getDeclaredFields();
		Class superclass = classO.getSuperclass();
		if(superclass != Object.class) {
			Field[] fieldsSuper = superclass.getDeclaredFields();
			res += getReturn(fieldsSuper, object, superclass);
			
			if(fields.length != 0 && !res.equals(""))
				res+=", ";
		}
			
		res += getReturn(fields, object, classO);
		return res;
	}
	
	public static String getReturn(Field[] fields, Object o, Class c) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String res = "";
		String ret = "";
		Field f;
		Method method;
		Object value;
		for (int j = 0; j<fields.length; j++) {
			f = fields[j];
			method = c.getMethod("get" +capitalize(f.getName()), null);
			value = method.invoke(o, null);
			if(value != null) {
				if(f.getType().isPrimitive()) {
					ret = value.toString();
				}else if ((value instanceof String || value instanceof Boolean
					|| value instanceof Enum || value instanceof Date || value instanceof LocalDate )){
					ret = "\"" + value.toString()+"\"";
				} else if (value instanceof List) { 
					ret = getList(f, value);
				} else {
					ret = JsonConverter.toJson(value);
				}
				res += "\"" + f.getName() + "\":" + ret ;
				if (j != fields.length - 1)
					res+=",";
			}	
		}
		return res;
	}

	public static String capitalize(String str){
	    if(str == null) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static String getList(Field f, Object value) {
		String ret = "[";
		List list = (List)value;
		if (!list.isEmpty()){
			if(f.getType().isPrimitive()) {
				ret+= list.get(0).toString();
			}else if ((list.get(0) instanceof String || list.get(0) instanceof Boolean
					|| list.get(0) instanceof Enum || list.get(0) instanceof Date )){
				for (Object obj : list) {
					ret += "\"" + obj.toString() + "\",";
					
				}
			} else if(list.get(0) instanceof Integer){
				for(Integer i :(List<Integer>)list) {
					ret+= i + ",";
				}
			}else {
				for (Object obj : list) {
					ret += JsonConverter.toJson(obj) + ",";
				}
			}
		}
			
		ret+="]";
		return ret;
	}
	
	
	public static String toJson(Object o) {
		if(o == null) {
			return "{}";
		}
		String res = "{";
		try {
			res+= withoutAccolade(o);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		res+="}";
		return res;
	}
	


	
	public static Map<String, Object> toObject(String json) {
		json = json.trim();
		if(json.startsWith("\"")) {
			Map<String, Object> map = new HashMap<>();
			map.put("String",json.substring(1, json.length()-1));
			return map;
		}
		if(json.startsWith("[") && json.endsWith("]")) {
			json = json.substring(1, json.length() - 1);
			Map<String, Object> map = new HashMap<>();
			map.put("List",toList(json));
			return map;
		}
		Map<String, Object> map = new HashMap<>();
		try{
			map.put("Integer",Integer.parseInt(json));
			return map;
		}catch(Exception e) {}
		try{
			map.put("Double",Double.parseDouble(json));
			return map;
		}catch(Exception e) {}
		if(json.equals("true")) {
			map.put("Boolean",true);
			return map;
		}
		if(json.equals("false")) {
			map.put("Boolean",false);
			return map;
		}
		if(json.startsWith("{") && json.endsWith("}")) {
			json = json.substring(1, json.length() - 1);
			List<String> fieldVal = split(json);
			List<String[]> l = new ArrayList<String[]>();
			for (String s : fieldVal) {
				l.add(s.split(":", 2));
			}
			for (String[] strings : l) {
				String field ;
				if(strings[0].startsWith("{")) {
					field = strings[0].trim().substring(2, strings[0].length() - 1);
				}else {
					field = strings[0].trim().substring(1, strings[0].length() - 1);
				}
				String value = strings[1].trim();
				map.put(field, toObject(value));
			}
		}else {
			map.put("Error", null);
		}
		return map;
	}

	private static List<Map<String, Object>> toList(String value) {
		//System.out.println("To list :: "+value);
		List<Map<String, Object>> list = new ArrayList<>();
		List<String> values = split(value);
		for (String string : values) {
			list.add(toObject(string));
		}
		return list;
	}
	
	private static List<String> split(String object) {
		List<String> list = new ArrayList<String>();
		String word = "";
		boolean inArray = false;
		boolean inObject = false;
		int countArray = 0;
		int countObject = 0;
		for(char c : object.toCharArray()) {
			if(c == '[') {
				countArray++;
				inArray = true;
			} else if(c == ']') {
				countArray--;
				if(countArray == 0) {
					inArray = false;
				}
			}
			if(c == '{') {
				countObject++;
				inObject = true;
			} else if(c == '}') {
				countObject--;
				if(countObject == 0) {
					inObject = false;
				}
			}
			if(c == ',' && !inArray &&!inObject) {
				list.add(word);
				word = "";
			}else {				
				word += c;
			}
		}
		if(!word.isEmpty()) list.add(word);
		if(list.get(list.size()-1).equals(" ")) {
			list.remove(list.size()-1);
		}
		return list;
	}
}

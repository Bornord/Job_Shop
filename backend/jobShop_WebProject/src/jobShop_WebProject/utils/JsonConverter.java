package jobShop_WebProject.utils;

import java.io.Serializable;
import java.lang.reflect.*;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
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
					|| value instanceof Enum || value instanceof Date )){
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
			} else {
				for (Object obj : list) {
					ret += JsonConverter.toJson(obj) + ",";
				}
			}
		}
			
		ret+="]";
		return ret;
	}
	
	
	public static String toJson(Object o) {
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
			List<String> fieldVal = splitInObject(json);
			List<String[]> l = new ArrayList<String[]>();
			for (String s : fieldVal) {
				l.add(s.split(":"));
			}
			for (String[] strings : l) {
				String field = strings[0].trim().substring(1, strings[0].length() - 1);
				String value = strings[1].trim();
				//System.out.println(field + " : " + toObject(value) + " on : " + value);
				map.put(field, toObject(value));
			}
		}else {
			map.put("Error", null);
		}
		return map;
	}

	private static List<Object> toList(String value) {
		List<Object> list = new ArrayList<Object>();
		String tmp = value.substring(1, value.length() - 1);
		List<String> values = splitInList(tmp);
		//System.out.println(values);
		for (String string : values) {
			list.add(toObject(string));
		}
		return list;
	}
	
	private static List<String> splitInObject(String object) {
		List<String> list = new ArrayList<String>();
		String word = "";
		boolean inArray = false;
		for(char c : object.toCharArray()) {
			if(c == '[') inArray = true;
			if(c == ']') inArray = false;
			if(c == ',' && !inArray) {
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
		//System.out.println(list);
		return list;
	}
	private static List<String> splitInList(String object) {
		List<String> list = new ArrayList<String>();
		String word = "";
		boolean inArray = false;
		for(char c : object.toCharArray()) {
			if(c == '{') inArray = true;
			if(c == '}') inArray = false;
			if(c == ',' && !inArray) {
				list.add(word);
				word = "";
			}else {				
				word += c;
			}
		}
		if(!word.isEmpty()) list.add(word);
		return list;
	}
}

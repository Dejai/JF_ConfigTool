import java.util.*; 

public class HTMLExamples {

	public static String [] examples;
	public static HashMap<String, String> htmlExamples = new HashMap<String, String>();

	HTMLExamples(){
		setHTMLExamples();
	}

	public static void setHTMLExamples(){
		htmlExamples.put(" ", " ");

		StringBuilder newLineEx = new StringBuilder();
			newLineEx.append("<html>");
			newLineEx.append("previous line of text...<br/>");
			newLineEx.append("<span style='font-weight:bold;color:green;'>&lt;br/&gt;</span>");
			newLineEx.append("<br/>next line of text ...");
			newLineEx.append("</html>");
		htmlExamples.put("Add New Line", newLineEx.toString());

		StringBuilder generalEx = new StringBuilder();
			generalEx.append("<html>");
			generalEx.append("&lt;span ");
			generalEx.append("<span style='font-weight:bold;color:green;'>style=\"...\"</span>");
			generalEx.append("&gt;<br/>");
			generalEx.append("&nbsp;&nbsp;text goes here <br/> &lt;span/&gt;");
			generalEx.append("<p style='color:red; font-size:8px;font-weight:bold;margin-top:10px;'>NOTE: You can add multiple styles.<br/>Just separate them with a semicolon.</p>");
			generalEx.append("</html>");
		htmlExamples.put("Add Style (in general)", generalEx.toString());

		StringBuilder colorEx = new StringBuilder();
			colorEx.append("<html>");
			colorEx.append("&lt;span ");
			colorEx.append("style=\"<span style='font-weight:bold;color:green;'>color:red;</span>\"");
			colorEx.append("&gt;<br/>");
			colorEx.append("&nbsp;&nbsp;text goes here <br/> &lt;span/&gt;");
			colorEx.append("<p style='color:red; font-size:8px;font-weight:bold;margin-top:10px;'>NOTE: You can enter most colors. <br/> Just enter the name.</p>");
			colorEx.append("</html>");
		htmlExamples.put("Change text color", colorEx.toString());


		StringBuilder italicsEx = new StringBuilder();
			italicsEx.append("<html>");
			italicsEx.append("&lt;span ");
			italicsEx.append("style=\"<span style='color:green;font-weight:bold;'>font-style:italics;</span>\"");
			italicsEx.append("&gt;<br/>");
			italicsEx.append("&nbsp;&nbsp;text goes here <br/>");
			italicsEx.append("&lt;span/&gt;");
			italicsEx.append("</html>");
		htmlExamples.put("Make text italics", italicsEx.toString());

		StringBuilder boldEx = new StringBuilder();
			boldEx.append("<html>");
			boldEx.append("&lt;span ");
			boldEx.append("style=\"<span style='color:green;font-weight:bold;'>font-weight:bold;</span>\"");
			boldEx.append("&gt;<br/>");
			boldEx.append("&nbsp;&nbsp;text goes here <br/>");
			boldEx.append("&lt;span/&gt;");
			boldEx.append("</html>");
		htmlExamples.put("Make text bold", boldEx.toString());

		examples = htmlExamples.keySet().toArray(new String[htmlExamples.size()]);
		Arrays.sort(examples);
		// helpDropDown = new JComboBox<String>(tempStringArr);
	}
}
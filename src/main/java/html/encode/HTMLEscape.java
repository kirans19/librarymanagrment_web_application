package html.encode;


public class HTMLEscape {

	public static String htmlEncode(String unEscapedString)
	  {
	   
	    String escapedHTML = StringUtils.encodeHtml(unEscapedString);
	     
	    return escapedHTML;
	  }
}

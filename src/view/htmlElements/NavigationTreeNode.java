package view.htmlElements;

public class NavigationTreeNode {

	public final static String CSS_CLASS = "navigation_tree_node";
	
	private String content;
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		
		return "<li><span class=\""+ CSS_CLASS +"\">"+ this.content +"</span></li>";
	}
	
	public static String renderContent(String content){
		
		return "<li><span class=\""+ CSS_CLASS +"\">"+ content +"</span></li>";
	}
}

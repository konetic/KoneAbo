

	import java.util.LinkedList;
	import java.util.*;

	import javax.swing.text.html.HTMLDocument.Iterator;

	public class RemoveEven {

		public static void main(String[] args){
		
			//ArrayList<String> list = new ArrayList<String>();
			LinkedList<String> list = new LinkedList<String>();
			
			list.add("was");
			list.add("wash");
			list.add("washi");
			list.add("washingt");
			list.add("washington");
			
			System.out.println(list);
			removeEvenLength(list);	
			System.out.println(list);
		}
		
		// Removes all strings of even length from
		// the given linked list.
	/*	public static void removeEvenLength(LinkedList<String> list) {
			
		Iterator <String> itr = list.iterator(); 
		
		while (itr.hasNext()) {
			
			String element = itr.next();//first element in the list
			if (element.length() % 2 == 0) {
			    itr.remove();
			}
		} 
		System.out.println(itr);
		}
		*/
		
		// Removes all strings of even length from
		// the given array list.
		public static void removeEvenLength(List<String> list) {
					
				for (int i = 0; i < list.size(); i += 2) { 
					String element = list.get(i);

				if (element.length() % 2 == 0) {
					list.remove(i);
					//i--;	
			}
			}
			}
	}


